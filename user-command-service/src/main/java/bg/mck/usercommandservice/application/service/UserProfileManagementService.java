package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.UserStatusDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.ProfileStatusUpdatedEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.exceptions.UserNotFoundException;
import bg.mck.usercommandservice.application.repository.UserRepository;


import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void updateUserProfile(Long id, UserStatusDTO dto) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        userEntity.setActive(dto.isActive());
        userRepository.save(userEntity);

        ProfileStatusUpdatedEvent event = new ProfileStatusUpdatedEvent(
              EventType.UserProfileUpdated,
                id,
                dto.isActive()
        );

        UserEvent<ProfileStatusUpdatedEvent> userEvent = EventCreationHelper.toUserEvent(event);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), event.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
