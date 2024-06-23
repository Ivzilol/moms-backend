package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.UserChangePasswordDTO;
import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.PasswordUpdateEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.exceptions.InvalidPasswordException;
import bg.mck.usercommandservice.application.exceptions.UserNotFoundException;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserChangePasswordService {

    private final UserRepository userRepository;
    private final UserQueryServiceClient userQueryClient;
    private final ObjectMapper objectMapper;

    public UserChangePasswordService(UserRepository userRepository, UserQueryServiceClient userQueryClient, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userQueryClient = userQueryClient;
        this.objectMapper = objectMapper;

    }

    public void changePassword(Long id, UserChangePasswordDTO userChangePasswordDTO) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        UserCredentialsDTO actualUserCredentials = userQueryClient.getUserCredentialsById(id);
        boolean isPasswordCorrect = BCrypt.checkpw(userChangePasswordDTO.getCurrentPassword(),
                actualUserCredentials.getHashedPassword());

        if (!isPasswordCorrect) {
            throw new InvalidPasswordException("Invalid password for user with email: " + actualUserCredentials.getEmail());
        }

        userEntity.setPassword(BCrypt.hashpw(userChangePasswordDTO.getNewPassword(),BCrypt.gensalt()));

        userRepository.save(userEntity);

        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent(EventType.UserPasswordUpdated,
                id,userEntity.getPassword());

        UserEvent<PasswordUpdateEvent> userEvent = EventCreationHelper.toUserEvent(passwordUpdateEvent);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), passwordUpdateEvent.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
