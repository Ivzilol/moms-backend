package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.constants.ApplicationConstants;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import bg.mck.userqueryservice.application.repository.EventRepository;
import bg.mck.userqueryservice.application.repository.UserRepository;
import bg.mck.userqueryservice.application.service.RedisService;
import bg.mck.userqueryservice.application.service.UserQueryService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Set;

import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.import=optional:file:../../application-env.properties")
class UserQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @MockBean
    private RedisService redisService;

    @Autowired
    private UserQueryService userQueryService;


    private UserEntity user;
    private static final String PASSWORD = "securePassword123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(redisService.getCachedObject(anyLong())).thenReturn(null);
        doNothing().when(redisService).cacheObject(ArgumentMatchers.any(UserEntity.class));

        user = userRegister();
        storeUserEvents(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    public void testGetUserCredentialsById_ShouldReturnUserCredentials() throws Exception {
        mockMvc.perform(get("/users/credentials/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.hashedPassword", is(user.getPassword())));

    }

    @Test
    public void testGetUserCredentialsById_NotFoundWhenInvalidUserId() throws Exception {
        mockMvc.perform(get("/users/credentials/" + user.getId() + 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUserDetailsById_ShouldReturnUserDetails() throws Exception {

        mockMvc
                .perform(get("/" + ApplicationConstants.APPLICATION_VERSION + "/user/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(user.getPhoneNumber())))
                .andExpect(jsonPath("$.isActive", is(user.isActive())))
                .andExpect(jsonPath("$.roles", containsInAnyOrder("user")));
    }

    @Test
    public void testGetUserDetailsById_NotFoundWhenInvalidUserId() throws Exception {
        mockMvc.perform(get("/" + ApplicationConstants.APPLICATION_VERSION + "/user/users/" + user.getId() + 1))
                .andExpect(status().isNotFound());
    }



    private UserEntity userRegister() {
        UserEntity user = new UserEntity();
        user.setId("102");
        user.setEmail("admin@abv.bg");
        user.setPassword(BCrypt.hashpw(PASSWORD, BCrypt.gensalt()));
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setPhoneNumber("0000000");
        user.setRoles(Set.of("user"));
        user.setActive(true);

        return userRepository.save(user);
    }

    private void storeUserEvents(UserEntity user) {
        RegisteredUserEvent registeredUserEvent = new RegisteredUserEvent();
        registeredUserEvent.setUserId(Long.valueOf(user.getId()));
        registeredUserEvent.setEmail(user.getEmail());
        registeredUserEvent.setPassword(user.getPassword());
        registeredUserEvent.setFirstName(user.getFirstName());
        registeredUserEvent.setLastName(user.getLastName());
        registeredUserEvent.setPhoneNumber(user.getPhoneNumber());
        registeredUserEvent.setActive(user.isActive());
        registeredUserEvent.setRoles(user.getRoles());

        UserEvent<RegisteredUserEvent> userRegisteredEvent = new UserEvent<>();
        userRegisteredEvent.setEvent(registeredUserEvent);
        userRegisteredEvent.setEventType(EventType.UserRegistered);
        userRegisteredEvent.setId("1");

        eventRepository.save(userRegisteredEvent);
    }
}