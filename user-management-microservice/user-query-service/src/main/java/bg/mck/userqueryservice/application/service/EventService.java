package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.ProfileUpdatedEvent;
import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {


    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RedisService redisService;

    public EventService(UserRepository userRepository, EventRepository eventRepository, RedisService redisService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.redisService = redisService;
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

    public <T extends BaseEvent> UserEvent<T> saveEvent(UserEvent<T> userEvent) {
       return eventRepository.save(userEvent);
    }


}
