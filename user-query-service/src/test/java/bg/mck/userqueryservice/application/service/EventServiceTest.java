package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.*;
import bg.mck.userqueryservice.application.exceptions.InvalidEventTypeException;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private RedisService redisService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UserRegistrationService userRegistrationService;

    @Spy
    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    public void testReconstructUserEntity_ShouldReturnUserEntity() {
        List<UserEvent<? extends BaseEvent>> userEvents = initUserEvents();

        UserEntity user = new UserEntity();
        user.setId("1");

        when(eventRepository.findByEventUserIdOrderByEventLocalDateTimeAsc(1L))
                .thenReturn(userEvents);

        doNothing().when(redisService).cacheObject(any(UserEntity.class));

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        UserEntity userEntity = eventService.reconstructUserEntity(1L);

        verify(userRepository).save(userEntity);
        verify(redisService).cacheObject(userEntity);

        assertEquals("1", userEntity.getId());
        assertEquals("John", userEntity.getFirstName());
        assertEquals("Doe", userEntity.getLastName());
        assertEquals("1234567890", userEntity.getPhoneNumber());
        assertTrue(userEntity.isActive());
        assertEquals(Set.of("user"), userEntity.getRoles());
        assertTrue(BCrypt.checkpw("newPassword", userEntity.getPassword()));
        assertEquals("test@test.com", userEntity.getEmail());
    }


    @Test
    public void testProcessUserEvent_UserProfileUpdatedEvent() throws Exception {
        ProfileStatusUpdatedEvent profileUpdatedEvent = new ProfileStatusUpdatedEvent();
        profileUpdatedEvent.setUserId(1L);
        profileUpdatedEvent.setActive(true);
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileStatusUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserProfileUpdated);
        userEvent.setId("2");

        String data = "{}";

        when(objectMapper.readValue(anyString(), any(TypeReference.class))).thenReturn(userEvent);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new UserEntity()));

        ArgumentCaptor<UserEvent<ProfileStatusUpdatedEvent>> eventCaptor = ArgumentCaptor.forClass(UserEvent.class);

        eventService.processUserEvent(data, EventType.UserProfileUpdated.name());

        verify(eventService).reconstructUserEntity(anyLong());
        verify(eventRepository).save(eventCaptor.capture());
        UserEvent<ProfileStatusUpdatedEvent> savedEvent = eventCaptor.getValue();

        assertEquals(userEvent, savedEvent);
    }

    @Test
    public void testProcessUserEvent_UserRegisteredEvent() throws Exception {
        RegisteredUserEvent registeredUserEvent = new RegisteredUserEvent();
        registeredUserEvent.setUserId(1L);
        registeredUserEvent.setEmail("test@test.com");
        registeredUserEvent.setFirstName("John");
        registeredUserEvent.setLastName("Doe");
        registeredUserEvent.setPhoneNumber("1234567890");
        registeredUserEvent.setActive(true);
        registeredUserEvent.setRoles(Set.of("user"));
        registeredUserEvent.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        registeredUserEvent.setLocalDateTime(LocalDateTime.now());
        registeredUserEvent.setEventType(EventType.UserRegistered);

        UserEvent<RegisteredUserEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(registeredUserEvent);
        userEvent.setEventType(EventType.UserRegistered);
        userEvent.setId("1");

        String data = "{}";

        when(objectMapper.readValue(anyString(), any(TypeReference.class))).thenReturn(userEvent);
        when(eventRepository.save(userEvent)).thenReturn(userEvent);

        ArgumentCaptor<UserEvent<RegisteredUserEvent>> eventCaptor = ArgumentCaptor.forClass(UserEvent.class);

        eventService.processUserEvent(data, EventType.UserRegistered.name());

        verify(eventRepository).save(eventCaptor.capture());
        verify(userRegistrationService).processUserRegister(registeredUserEvent);

        UserEvent<RegisteredUserEvent> savedEvent = eventCaptor.getValue();

        assertEquals(userEvent, savedEvent);

    }

    @Test
    public void testProcessUserEvent_UserPasswordUpdatedEvent() throws Exception {
        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(1L);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newPassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<PasswordUpdateEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(passwordUpdateEvent);
        userEvent.setEventType(EventType.UserPasswordUpdated);
        userEvent.setId("3");

        String data = "{}";

        when(objectMapper.readValue(anyString(), any(TypeReference.class))).thenReturn(userEvent);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new UserEntity()));

        ArgumentCaptor<UserEvent<PasswordUpdateEvent>> eventCaptor = ArgumentCaptor.forClass(UserEvent.class);

        eventService.processUserEvent(data, EventType.UserPasswordUpdated.name());

        verify(eventService).reconstructUserEntity(anyLong());
        verify(eventRepository).save(eventCaptor.capture());


        UserEvent<PasswordUpdateEvent> savedEvent = eventCaptor.getValue();

        assertEquals(userEvent, savedEvent);
    }

    @Test
    public void testProcessUserEvent_InvalidEventType() {
        String invalidEventType = "InvalidEvent";
        String data = "{}";

        assertThrows(InvalidEventTypeException.class, () -> {
            eventService.processUserEvent(data, invalidEventType);
        });
    }

    @Test
    public void testDoesUserExist_UserNotFound(){
        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(1L);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newPassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<PasswordUpdateEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(passwordUpdateEvent);
        userEvent.setEventType(EventType.UserPasswordUpdated);
        userEvent.setId("3");

        String data = "{}";

        assertThrows(InvalidEventTypeException.class, () -> {
            eventService.processUserEvent(data, "invalid-event-type");
        });
    }


    private List<UserEvent<? extends BaseEvent>> initUserEvents() {
        RegisteredUserEvent registeredUserEvent = new RegisteredUserEvent();
        registeredUserEvent.setUserId(1L);
        registeredUserEvent.setEmail("test@test.com");
        registeredUserEvent.setFirstName("John");
        registeredUserEvent.setLastName("Doe");
        registeredUserEvent.setPhoneNumber("1234567890");
        registeredUserEvent.setActive(true);
        registeredUserEvent.setRoles(Set.of("user"));
        registeredUserEvent.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        registeredUserEvent.setLocalDateTime(LocalDateTime.now());
        registeredUserEvent.setEventType(EventType.UserRegistered);

        UserEvent<RegisteredUserEvent> userEvent1 = new UserEvent<>();
        userEvent1.setEvent(registeredUserEvent);
        userEvent1.setEventType(EventType.UserRegistered);
        userEvent1.setId("1");

        ProfileStatusUpdatedEvent profileUpdatedEvent = new ProfileStatusUpdatedEvent();
        profileUpdatedEvent.setUserId(1L);
        profileUpdatedEvent.setActive(true);
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileStatusUpdatedEvent> userEvent2 = new UserEvent<>();
        userEvent2.setEvent(profileUpdatedEvent);
        userEvent2.setEventType(EventType.UserProfileUpdated);
        userEvent2.setId("2");

        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(1L);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newPassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<PasswordUpdateEvent> userEvent3 = new UserEvent<>();
        userEvent3.setEvent(passwordUpdateEvent);
        userEvent3.setEventType(EventType.UserPasswordUpdated);
        userEvent3.setId("3");

        return List.of(userEvent1, userEvent2, userEvent3);
    }
}