package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.*;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {


    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;
    private final UserRegistrationService userRegistrationService;

    public EventService(UserRepository userRepository, EventRepository eventRepository, RedisService redisService, ObjectMapper objectMapper, UserRegistrationService userRegistrationService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.objectMapper = objectMapper;
        this.userRegistrationService = userRegistrationService;
    }


    public UserEntity reconstructUserEntity(Long userId) {
        List<UserEvent<? extends BaseEvent>> events = eventRepository.
                findByEventUserIdOrderByEventLocalDateTimeAsc(userId);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(String.valueOf(userId));

        for (var event : events) {
            applyEvent(event, userEntity);
        }

        userRepository.save(userEntity);
        redisService.cacheObject(userEntity);

        return userEntity;
    }


    public <T extends BaseEvent> void processUserEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.UserProfileUpdated.name())) {
            UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
            });

            saveEvent(userEvent);
            reconstructUserEntity(userEvent.getEvent().getUserId());
        } else if (eventType.equals(EventType.UserRegistered.name())) {

            UserEvent<RegisteredUserEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
            });

            UserEvent<RegisteredUserEvent> savedEvent = saveEvent(userEvent);
            userRegistrationService.processUserRegister(savedEvent.getEvent());

        } else if (eventType.equals(EventType.UserPasswordUpdated.name())) {
            UserEvent<PasswordUpdateEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
            });
            saveEvent(userEvent);
            reconstructUserEntity(userEvent.getEvent().getUserId());
        } else {
            throw new IllegalArgumentException("Unknown event type: " + eventType);
        }
    }

    private <T extends BaseEvent> UserEvent<T> saveEvent(UserEvent<T> userEvent) {
        return eventRepository.save(userEvent);
    }

    private void applyEvent(UserEvent<? extends BaseEvent> userEvent, UserEntity userEntity) {
        BaseEvent event = userEvent.getEvent();

        if (event instanceof ProfileUpdatedEvent updateEvent) {
            userEntity.setFirstName(updateEvent.getFirstName());
            userEntity.setLastName(updateEvent.getLastName());
            userEntity.setPhoneNumber(updateEvent.getPhoneNumber());

        } else if (event instanceof RegisteredUserEvent registerEvent) {
            userEntity.setId(String.valueOf(registerEvent.getUserId()));
            userEntity.setEmail(registerEvent.getEmail());
            userEntity.setPassword(registerEvent.getPassword());
            userEntity.setFirstName(registerEvent.getFirstName());
            userEntity.setLastName(registerEvent.getLastName());
            userEntity.setPhoneNumber(registerEvent.getPhoneNumber());
            userEntity.setActive(registerEvent.isActive());
            userEntity.setRoles(registerEvent.getRoles());
        }
    }
}
