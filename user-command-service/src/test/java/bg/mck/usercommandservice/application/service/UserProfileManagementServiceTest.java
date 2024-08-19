package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
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
import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileManagementServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserQueryServiceClient userQueryClient;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserProfileManagementService userProfileManagementService;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {


        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority().setAuthority(AuthorityEnum.USER));

        userEntity = new UserEntity()
                .setId(1L)
                .setEmail("test@test.com")
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("1234567890")
                .setActive(true)
                .setAuthorities(authorities);
    }

    @Test
    public void testUpdateUserStatus_ShouldUpdateStatusAndSendEvent() throws JsonProcessingException {
        UserStatusUpdatedDTO dto = new UserStatusUpdatedDTO();
        dto.setActive(false);

        ProfileStatusUpdatedEvent event = new ProfileStatusUpdatedEvent(EventType.UserStatusUpdated, 1L, false);
        UserEvent<ProfileStatusUpdatedEvent> userEvent = new UserEvent<>(EventType.UserStatusUpdated, event);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(objectMapper.writeValueAsString(any(UserEvent.class))).thenReturn("mockedJsonString");

        userProfileManagementService.updateUserStatus(1L, dto);

        assertFalse(userEntity.isActive());
        verify(userRepository).save(userEntity);

        ArgumentCaptor<UserEvent<ProfileStatusUpdatedEvent>> captor = ArgumentCaptor.forClass(UserEvent.class);
        verify(objectMapper).writeValueAsString(captor.capture());
        verify(userQueryClient).sendEvent(eq("mockedJsonString"), eq(EventType.UserStatusUpdated.name()));

        UserEvent<ProfileStatusUpdatedEvent> capturedEvent = captor.getValue();
        assertEquals(EventType.UserStatusUpdated, capturedEvent.getEventType());
        assertEquals(1L, capturedEvent.getEvent().getUserId());
        assertFalse(capturedEvent.getEvent().isActive());
    }

    @Test
    public void testUpdateUserStatus_UserNotFound_ShouldThrowException() {
        UserStatusUpdatedDTO dto = new UserStatusUpdatedDTO();
        dto.setActive(false);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userProfileManagementService.updateUserStatus(1L, dto));
    }

    @Test
    public void testUpdateUserProfile_ShouldUpdateProfileAndSendEvent() throws JsonProcessingException {
        UserUpdatedDTO dto = new UserUpdatedDTO();
        dto.setFirstName("Jane");
        dto.setLastName("Smith");
        dto.setPhoneNumber("0987654321");
        dto.setEmail("jane@smith.com");
        dto.setRole(AuthorityEnum.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER))
                .thenReturn(new Authority().setAuthority(AuthorityEnum.USER));
        when(authorityRepository.getAuthorityByAuthority(AuthorityEnum.ADMIN))
                .thenReturn(new Authority().setAuthority(AuthorityEnum.ADMIN));

        userProfileManagementService.updateUserProfile(1L, dto);

        assertEquals("Jane", userEntity.getFirstName());
        assertEquals("Smith", userEntity.getLastName());
        assertEquals("0987654321", userEntity.getPhoneNumber());
        assertEquals("jane@smith.com", userEntity.getEmail());
        assertEquals(2, userEntity.getAuthorities().size());

        verify(userRepository).save(userEntity);

        ArgumentCaptor<UserEvent<ProfileUpdatedEvent>> captor = ArgumentCaptor.forClass(UserEvent.class);
        verify(userQueryClient).sendEvent(anyString(), eq(EventType.UserProfileUpdated.name()));
        verify(objectMapper).writeValueAsString(captor.capture());

        UserEvent<ProfileUpdatedEvent> capturedEvent = captor.getValue();
        assertEquals(EventType.UserProfileUpdated, capturedEvent.getEventType());
        assertEquals(1L, capturedEvent.getEvent().getUserId());
        assertEquals("Jane", capturedEvent.getEvent().getFirstName());
        assertEquals("Smith", capturedEvent.getEvent().getLastName());
        assertEquals("0987654321", capturedEvent.getEvent().getPhoneNumber());
        assertEquals(Set.of("USER", "ADMIN"), capturedEvent.getEvent().getRoles());
    }

    @Test
    public void testUpdateUserProfile_UserNotFound_ShouldThrowException() {
        UserUpdatedDTO dto = new UserUpdatedDTO();
        dto.setFirstName("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userProfileManagementService.updateUserProfile(1L, dto));
    }
}
