package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    public void testProcessUserRegister_ShouldRegisterUser() {
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        RegisteredUserEvent event = new RegisteredUserEvent()
                .setEmail("admin@abv.bg")
                .setPassword("parola")
                .setFirstName("admin")
                .setLastName("admin")
                .setActive(true)
                .setPhoneNumber("00000");
        event.setUserId(1L);

        userRegistrationService.processUserRegister(event);

        verify(userRepository).save(captor.capture());

        UserEntity userEntity = captor.getValue();

        assertEquals(event.getEmail(), userEntity.getEmail());
        assertEquals(event.getPassword(), userEntity.getPassword());
        assertEquals(event.getFirstName(), userEntity.getFirstName());
        assertEquals(event.getLastName(), userEntity.getLastName());
        assertEquals(event.isActive(), userEntity.isActive());
        assertEquals(event.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(String.valueOf(event.getUserId()), userEntity.getId());
    }
}