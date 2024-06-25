package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.*;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import bg.mck.userqueryservice.application.service.EventService;
import bg.mck.userqueryservice.application.service.RedisService;
import bg.mck.userqueryservice.application.service.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.import=optional:file:../../application-env.properties")
public class UserEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private RedisService redisService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private EventService eventService;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        MockitoAnnotations.openMocks(this);

        when(redisService.getCachedObject(anyLong())).thenReturn(null);
        doNothing().when(redisService).cacheObject(any(UserEntity.class));

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
    public void testProcessUserEvent_UpdateProfileEvent() throws Exception {
        ProfileUpdatedEvent profileUpdatedEvent = new ProfileUpdatedEvent();
        profileUpdatedEvent.setUserId(1L);
        profileUpdatedEvent.setFirstName("newName");
        profileUpdatedEvent.setLastName("newLastName");
        profileUpdatedEvent.setPhoneNumber("00000000");
        profileUpdatedEvent.setLocalDateTime(LocalDateTime.now());

        UserEvent<ProfileUpdatedEvent> userEvent = new UserEvent<>();
        userEvent.setEvent(profileUpdatedEvent);
        userEvent.setEventType(EventType.UserProfileUpdated);
        userEvent.setId("2");

        String data = objectMapper.writeValueAsString(userEvent);

        mockMvc.perform(post("/users/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", EventType.UserProfileUpdated.name())
                        .content(data))
                .andExpect(status().isOk());

        UserEntity userEntity = userRepository.findById(profileUpdatedEvent.getUserId().toString()).orElse(null);
        assertNotNull(userEntity);
        assertEquals(profileUpdatedEvent.getFirstName(), userEntity.getFirstName());
        assertEquals(profileUpdatedEvent.getLastName(), userEntity.getLastName());
        assertEquals(profileUpdatedEvent.getPhoneNumber(), userEntity.getPhoneNumber());

        UserEvent<? extends BaseEvent> savedEvent = eventRepository.findById(userEvent.getId()).get();
        assertEquals(profileUpdatedEvent, savedEvent.getEvent());
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