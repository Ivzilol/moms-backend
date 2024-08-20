package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserStatusUpdatedDTO;
import bg.mck.usercommandservice.application.dto.UserUpdatedDTO;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.ProfileStatusUpdatedEvent;
import bg.mck.usercommandservice.application.events.ProfileUpdatedEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.exceptions.UserNotFoundException;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.service.UserProfileManagementService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserProfileManagementService userProfileManagementService;

    @MockBean
    private UserQueryServiceClient userQueryClient;



    @BeforeEach
    void setUp() {

//        Authority authority = new Authority();
//        authority.setAuthority(AuthorityEnum.SUPERADMIN);
//        authorityRepository.save(authority);
//
//        Authority authorityAdmin = new Authority();
//        authorityAdmin.setAuthority(AuthorityEnum.ADMIN);
//        authorityRepository.save(authorityAdmin);
//
//        Authority authorityUser = new Authority();
//        authorityUser.setAuthority(AuthorityEnum.USER);
//        authorityRepository.save(authorityUser);

        UserEntity user = new UserEntity();
        user.setId(5L);
        user.setEmail("oldemail@example.com");
        user.setPassword("parola");
        user.setFirstName("Old");
        user.setLastName("Name");
        user.setPhoneNumber("1234567890");
        user.setAuthorities(Set.of(authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER)));
        user.setActive(true);
        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {
      userRepository.deleteById(5L);
    }


    @Test
    void testUpdateUserStatus_ValidRequest_ShouldReturnOkAndUpdateStatusAndSendEvent() throws Exception {
        UserStatusUpdatedDTO statusUpdatedDTO = new UserStatusUpdatedDTO().setActive(false);

        doNothing().when(userQueryClient).sendEvent(anyString(), eq("UserStatusUpdated"));

        UserEntity userBefore = userRepository.findById(2L).orElse(null);
        assertNotNull(userBefore);
        assertTrue(userBefore.isActive());

        // Act
        mockMvc.perform(patch("/v1/superadmin/user/command/profile/status/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(statusUpdatedDTO)))
                .andExpect(status().isOk());

        // Assert: Verify that the user status was updated in the database
        UserEntity userAfter = userRepository.findById(2L).orElse(null);
        assertNotNull(userAfter);
        assertFalse(userAfter.isActive());

        // Assert: Capture the argument as a JSON string
        ArgumentCaptor<String> jsonCaptor = ArgumentCaptor.forClass(String.class);
        verify(userQueryClient).sendEvent(jsonCaptor.capture(), eq("UserStatusUpdated"));

        // Deserialize the JSON string to a UserEvent
        UserEvent<ProfileStatusUpdatedEvent> userEvent = objectMapper.readValue(jsonCaptor.getValue(),
                new TypeReference<>() {});

        // Assert: Verify the content of the deserialized UserEvent
        assertEquals(2L, userEvent.getEvent().getUserId());
        assertFalse(userEvent.getEvent().isActive());
    }

    @Test
    void testUpdateUserStatus_InvalidUserId_ShouldReturnNotFoundAndThrowException() throws Exception {
        UserStatusUpdatedDTO statusUpdatedDTO = new UserStatusUpdatedDTO().setActive(false);

        UserEntity userBefore = userRepository.findById(100L).orElse(null);
        assertNull(userBefore);

        // Act
        mockMvc.perform(patch("/v1/superadmin/user/command/profile/status/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(statusUpdatedDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));

    }

    @Test
    void testUpdateUserProfile_ValidRequest_ShouldReturnOkAndUpdateProfile() throws Exception {
        // Arrange
        UserUpdatedDTO updatedDTO = new UserUpdatedDTO()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("johndoe@example.com")
                .setPhoneNumber("0987654321")
                .setRole(AuthorityEnum.ADMIN);

        doNothing().when(userQueryClient).sendEvent(anyString(), eq("UserProfileUpdated"));

        // Act
        mockMvc.perform(patch("/v1/superadmin/user/command/profile/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk());

        // Assert: Verify that the user profile was updated in the database
        UserEntity updatedUser = userRepository.findById(2L).orElse(null);
        assertNotNull(updatedUser);
        assertEquals("John", updatedUser.getFirstName());
        assertEquals("Doe", updatedUser.getLastName());
        assertEquals("johndoe@example.com", updatedUser.getEmail());
        assertEquals("0987654321", updatedUser.getPhoneNumber());
        assertTrue(updatedUser.getAuthorities().contains(authorityRepository.getAuthorityByAuthority(AuthorityEnum.ADMIN)));

        ArgumentCaptor<String> jsonCaptor = ArgumentCaptor.forClass(String.class);
        verify(userQueryClient).sendEvent(jsonCaptor.capture(), eq("UserProfileUpdated"));

        UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(jsonCaptor.getValue(),
                new TypeReference<>() {});

        assertEquals(2L, userEvent.getEvent().getUserId());
        assertEquals(updatedDTO.getEmail(), userEvent.getEvent().getEmail());
        assertEquals(updatedDTO.getFirstName(), userEvent.getEvent().getFirstName());
        assertEquals(updatedDTO.getLastName(), userEvent.getEvent().getLastName());
        assertEquals(updatedDTO.getPhoneNumber(), userEvent.getEvent().getPhoneNumber());

    }

    @Test
    void testUpdateUserProfile_InvalidUserId_ShouldReturnNotFoundAndThrowException() throws Exception {
        UserUpdatedDTO updatedDTO = new UserUpdatedDTO()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("johndoe@example.com")
                .setPhoneNumber("0987654321")
                .setRole(AuthorityEnum.ADMIN);

        UserEntity userBefore = userRepository.findById(100L).orElse(null);
        assertNull(userBefore);

        // Act
        mockMvc.perform(patch("/v1/superadmin/user/command/profile/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));

    }
}
