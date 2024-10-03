package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.*;
import bg.mck.userqueryservice.application.exceptions.InvalidEventTypeException;
import bg.mck.userqueryservice.application.exceptions.UserNotFoundException;
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
    private final ObjectMapper objectMapper;
    private final UserRegistrationService userRegistrationService;

    public EventService(UserRepository userRepository, EventRepository eventRepository, ObjectMapper objectMapper, UserRegistrationService userRegistrationService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
        this.userRegistrationService = userRegistrationService;
    }


    public UserEntity reconstructUserEntity(Long userId) {
        doesUserExist(userId);
        List<UserEvent<? extends BaseEvent>> events = eventRepository.
                findByEventUserIdOrderByEventLocalDateTimeAsc(userId);


        UserEntity userEntity = new UserEntity();
        userEntity.setId(String.valueOf(userId));

        for (var event : events) {
            applyEvent(event, userEntity);
        }

        userRepository.save(userEntity);

        return userEntity;
    }


    public void processUserEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.UserStatusUpdated.name())) {
            UserEvent<ProfileStatusUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
            });

            doesUserExist(userEvent.getEvent().getUserId());
            saveEvent(userEvent);
            reconstructUserEntity(userEvent.getEvent().getUserId());
        } else  if (eventType.equals(EventType.UserProfileUpdated.name())) {
            UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
            });

            doesUserExist(userEvent.getEvent().getUserId());
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

            doesUserExist(userEvent.getEvent().getUserId());
            saveEvent(userEvent);
            reconstructUserEntity(userEvent.getEvent().getUserId());
        } else {
            throw new InvalidEventTypeException("Unknown event type: " + eventType);
        }
    }



    private <T extends BaseEvent> UserEvent<T> saveEvent(UserEvent<T> userEvent) {
        return eventRepository.save(userEvent);
    }


    private void applyEvent(UserEvent<? extends BaseEvent> userEvent, UserEntity userEntity) {
        BaseEvent event = userEvent.getEvent();

        if (event instanceof ProfileStatusUpdatedEvent updateEvent) {
            userEntity.setActive(updateEvent.isActive());

        } else if (event instanceof ProfileUpdatedEvent updateEvent) {
            userEntity.setEmail(updateEvent.getEmail());
            userEntity.setFirstName(updateEvent.getFirstName());
            userEntity.setLastName(updateEvent.getLastName());
            userEntity.setRoles(updateEvent.getRoles());
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
        } else if (event instanceof PasswordUpdateEvent passwordEvent) {
            userEntity.setPassword(passwordEvent.getNewPassword());
        }
    }

    private void doesUserExist(Long userId) {
        userRepository.findById(userId.toString()).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " doesn't exist"));
    }
}
