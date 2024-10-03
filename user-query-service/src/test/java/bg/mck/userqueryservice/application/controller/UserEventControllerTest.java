package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.*;
import bg.mck.userqueryservice.application.exceptions.InvalidEventTypeException;
import bg.mck.userqueryservice.application.exceptions.UserNotFoundException;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import bg.mck.userqueryservice.application.service.EventService;
import bg.mck.userqueryservice.application.service.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.import=optional:file:../../../../../../../../../application-env.properties")
public class UserEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private EventService eventService;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
//        MockitoAnnotations.openMocks(this);

        if (!testInfo.getTestMethod().get().getName().equals("testProcessUserEvent_RegisterEvent")) {
            registerUserEvent();
        }
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    public void testProcessUserEvent_RegisterEvent() throws Exception {
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

        String data = objectMapper.writeValueAsString(userEvent);
        assertNull(userRepository.findById(registeredUserEvent.getUserId().toString()).orElse(null));

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserRegistered.name())
                        .content(data))
                .andExpect(status().isOk());


        UserEntity userEntity = userRepository.findById(registeredUserEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntity);
        assertEquals(registeredUserEvent.getEmail(), userEntity.getEmail());
        assertEquals(registeredUserEvent.getUserId().toString(), userEntity.getId());
        assertEquals(registeredUserEvent.getFirstName(), userEntity.getFirstName());
        assertEquals(registeredUserEvent.getLastName(), userEntity.getLastName());
        assertEquals(registeredUserEvent.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(registeredUserEvent.isActive(), userEntity.isActive());
        assertEquals(registeredUserEvent.getRoles(), userEntity.getRoles());
        assertTrue(BCrypt.checkpw("admin", userEntity.getPassword()));

        UserEvent<? extends BaseEvent> savedEvent = eventRepository.findById(userEvent.getId()).get();

        assertEquals(registeredUserEvent, savedEvent.getEvent());
    }




    @Test
    public void testProcessUserEvent_UpdateProfileStatus() throws Exception {
        ProfileStatusUpdatedEvent profileUpdatedEvent = new ProfileStatusUpdatedEvent();
        profileUpdatedEvent.setUserId(1L);
        profileUpdatedEvent.setActive(false);
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileStatusUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserStatusUpdated);
        userEvent.setId("200");

        UserEntity userEntityBefore = userRepository.findById(profileUpdatedEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntityBefore);
        assertTrue(userEntityBefore.isActive());

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserStatusUpdated.name())
                        .content(data))
                .andExpect(status().isOk());

        UserEntity userEntityAfter = userRepository.findById(profileUpdatedEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntityAfter);
        assertFalse(userEntityAfter.isActive());


        UserEvent<? extends BaseEvent> savedEvent = eventRepository.findById(userEvent.getId()).get();
        ProfileStatusUpdatedEvent event = (ProfileStatusUpdatedEvent) savedEvent.getEvent();
        assertEquals(userEvent.getId(), savedEvent.getId());
        assertEquals(profileUpdatedEvent.isActive(), event.isActive());
        assertEquals(profileUpdatedEvent.getEventType(), event.getEventType());
    }

    @Test
    public void testProcessUserEvent_UpdateProfileStatus_InvalidUser_ThrowsException() throws Exception {
        ProfileStatusUpdatedEvent profileUpdatedEvent = new ProfileStatusUpdatedEvent();
        profileUpdatedEvent.setUserId(1000L);
        profileUpdatedEvent.setActive(false);
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileStatusUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserProfileUpdated);
        userEvent.setId("200");


        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserStatusUpdated.name())
                        .content(data))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));
    }


    @Test
    public void testProcessUserEvent_UpdateProfile() throws Exception {
        ProfileUpdatedEvent profileUpdatedEvent = new ProfileUpdatedEvent();
        profileUpdatedEvent.setUserId(1L);
        profileUpdatedEvent.setEmail("newEmail@email.bg");
        profileUpdatedEvent.setFirstName("firstName");
        profileUpdatedEvent.setLastName("lastName");
        profileUpdatedEvent.setPhoneNumber("0000000");
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserProfileUpdated);
        userEvent.setId("200");

        UserEntity userEntityBefore = userRepository.findById(profileUpdatedEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntityBefore);
        assertEquals("test@test.com", userEntityBefore.getEmail());

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserProfileUpdated.name())
                        .content(data))
                .andExpect(status().isOk());

        UserEntity userEntityAfter = userRepository.findById(profileUpdatedEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntityAfter);
        assertEquals(profileUpdatedEvent.getEmail(), userEntityAfter.getEmail());
        assertEquals(profileUpdatedEvent.getFirstName(), userEntityAfter.getFirstName());
        assertEquals(profileUpdatedEvent.getLastName(), userEntityAfter.getLastName());
        assertEquals(profileUpdatedEvent.getPhoneNumber(), userEntityAfter.getPhoneNumber());


        UserEvent<? extends BaseEvent> savedEvent = eventRepository.findById(userEvent.getId()).get();
        ProfileUpdatedEvent event = (ProfileUpdatedEvent) savedEvent.getEvent();
        assertEquals(userEvent.getId(), savedEvent.getId());
        assertEquals(profileUpdatedEvent.getFirstName(), event.getFirstName());
        assertEquals(profileUpdatedEvent.getLastName(), event.getLastName());
        assertEquals(profileUpdatedEvent.getPhoneNumber(), event.getPhoneNumber());
        assertEquals(profileUpdatedEvent.getEmail(), event.getEmail());
    }

    @Test
    public void testProcessUserEvent_UpdateProfile_InvalidUser_ThrowsException() throws Exception {
        ProfileUpdatedEvent profileUpdatedEvent = new ProfileUpdatedEvent();
        profileUpdatedEvent.setUserId(1000L);
        profileUpdatedEvent.setEmail("newEmail@email.bg");
        profileUpdatedEvent.setFirstName("firstName");
        profileUpdatedEvent.setLastName("lastName");
        profileUpdatedEvent.setPhoneNumber("0000000");
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserProfileUpdated);
        userEvent.setId("200");


        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserProfileUpdated.name())
                        .content(data))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));
    }




    @Test
    public void testProcessUserEvent_PasswordUpdateEvent() throws Exception {
        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(1L);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newpassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());


        UserEvent<PasswordUpdateEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(passwordUpdateEvent);
        userEvent.setEventType(EventType.UserPasswordUpdated);
        userEvent.setId("3");

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserPasswordUpdated.name())
                        .content(data))
                .andExpect(status().isOk());

        UserEntity userEntity = userRepository.findById(passwordUpdateEvent.getUserId().toString()).orElse(null);

        assertNotNull(userEntity);
        assertEquals(passwordUpdateEvent.getUserId().toString(), userEntity.getId());
        assertTrue(BCrypt.checkpw("newpassword", userEntity.getPassword()));

        UserEvent<? extends BaseEvent> savedEvent = eventRepository.findById(userEvent.getId()).get();
        assertEquals(passwordUpdateEvent, savedEvent.getEvent());
    }

    @Test
    public void testProcessUserEvent_ShouldThrowWhenInvalidUserId() throws Exception {
        Long invalidUserId = 20L;
        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(invalidUserId);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newpassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());


        UserEvent<PasswordUpdateEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(passwordUpdateEvent);
        userEvent.setEventType(EventType.UserPasswordUpdated);
        userEvent.setId("4");

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserPasswordUpdated.name())
                        .content(data))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));

    }

    @Test
    public void testProcessUserEvent_ShouldThrowWhenInvalidEventType() throws Exception {
        PasswordUpdateEvent passwordUpdateEvent = new PasswordUpdateEvent();
        passwordUpdateEvent.setUserId(1L);
        passwordUpdateEvent.setNewPassword(BCrypt.hashpw("newpassword", BCrypt.gensalt()));
        passwordUpdateEvent.setLocalDateTime(LocalDateTime.now());


        UserEvent<PasswordUpdateEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(passwordUpdateEvent);
        userEvent.setEventType(EventType.UserPasswordUpdated);
        userEvent.setId("4");

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "invalid-event-type")
                        .content(data))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(InvalidEventTypeException.class, result.getResolvedException()));

    }

    private void registerUserEvent() {
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

        try {
            String data = objectMapper.writeValueAsString(userEvent);
            mockMvc.perform(post("/users/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Event-Type", EventType.UserRegistered.name())
                            .content(data))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail("Failed to register user for test setup: " + e.getMessage());
        }
    }


}