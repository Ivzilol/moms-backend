package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.dto.UserDetailsDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.ProfileUpdatedEvent;
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
public class UserProfileManagementService {

    private final UserRepository userRepository;
    private final UserQueryServiceClient userQueryClient;
    private final ObjectMapper objectMapper;

    public UserProfileManagementService(UserRepository userRepository, UserQueryServiceClient userQueryClient, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userQueryClient = userQueryClient;
        this.objectMapper = objectMapper;
    }

    public void updateUserProfile(Long id, UserDetailsDTO dto) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        UserCredentialsDTO actualUserCredentials = userQueryClient.getUserCredentialsById(id);
        boolean isPasswordCorrect = BCrypt.checkpw(dto.getPassword(), actualUserCredentials.getHashedPassword());

        if (!isPasswordCorrect) {
            throw new InvalidPasswordException("Invalid password for USER with email: " + actualUserCredentials.getEmail());
        }

        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(userEntity);

        ProfileUpdatedEvent event = new ProfileUpdatedEvent(
                EventType.UserProfileUpdated,
                id,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber()
        );

        UserEvent<ProfileUpdatedEvent> userEvent = EventCreationHelper.toUserEvent(event);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), event.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
