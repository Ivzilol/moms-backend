package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.NotificationServiceClient;
import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.ForgotPasswordEmailDTO;
import bg.mck.usercommandservice.application.dto.ResetPasswordDTO;
import bg.mck.usercommandservice.application.entity.ForgotPassword;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.PasswordUpdateEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.exceptions.EmailNotFoundException;
import bg.mck.usercommandservice.application.exceptions.InvalidTokenException;
import bg.mck.usercommandservice.application.exceptions.ResetPasswordAlreadySendException;
import bg.mck.usercommandservice.application.repository.ForgotPasswordRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ForgotPasswordService {

    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserRepository userRepository;
    private final NotificationServiceClient notificationServiceClient;
    private final UserQueryServiceClient userQueryServiceClient;
    private final ObjectMapper objectMapper;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public ForgotPasswordService(ForgotPasswordRepository forgotPasswordRepository, UserRepository userRepository, NotificationServiceClient notificationServiceClient, UserQueryServiceClient userQueryServiceClient, ObjectMapper objectMapper) {
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.userRepository = userRepository;
        this.notificationServiceClient = notificationServiceClient;
        this.userQueryServiceClient = userQueryServiceClient;
        this.objectMapper = objectMapper;
    }


    @Transactional
    public void createResetPassword(String email) {
        UserEntity user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException();
        }

        ForgotPassword exist = forgotPasswordRepository.findByUserEmail(email);
        if (exist != null) {
            throw new ResetPasswordAlreadySendException();
        }

        String uuid = UUID.randomUUID().toString();

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setUserEmail(user.getEmail());
        forgotPassword.setUuid(uuid);

        forgotPasswordRepository.save(forgotPassword);
        ForgotPasswordEmailDTO toSend = new ForgotPasswordEmailDTO(user.getEmail(), uuid);

        CompletableFuture.runAsync(() -> notificationServiceClient.sendResetPassword(toSend), executor)
                .orTimeout(10, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    executor.shutdownNow();
                    return null;
                });
    }

    @PreDestroy
    public void shutDown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(4, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    @Transactional
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) throws JsonProcessingException {
        ForgotPassword entity = forgotPasswordRepository.findByUuid(resetPasswordDTO.getToken());
        if (entity == null) {
            throw new InvalidTokenException();
        }

        UserEntity user = userRepository.findByEmail(entity.getUserEmail());
        user.setPassword(BCrypt.hashpw(resetPasswordDTO.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        forgotPasswordRepository.delete(entity);

        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setNewPassword(user.getPassword());
        passwordUpdateEvent.setEventType(EventType.UserPasswordUpdated);
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());
        passwordUpdateEvent.setUserId(user.getId());
        UserEvent<PasswordUpdateEvent> event = new UserEvent<>();
        event.setEvent(passwordUpdateEvent);
        event.setEventType(EventType.UserPasswordUpdated);
        userQueryServiceClient.sendEvent(objectMapper.writeValueAsString(event), event.getEventType().name());
    }
}
