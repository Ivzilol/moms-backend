package bg.mck.usercommandservice.UnitTests;

import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.service.UserRegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRegisterServiceTests {

    @Mock
    private UserRepository userCommandRepository;

    @InjectMocks
    private UserRegisterService userRegisterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findUserByEmail_NullOrEmptyValue_ShouldReturnNull() {

        String result = userRegisterService.findUserByEmail(null);
        assertNull(result);

        result = userRegisterService.findUserByEmail("");
        assertNull(result);

        result = userRegisterService.findUserByEmail("   ");
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserNotFound_ShouldReturnNull() {
        when(userCommandRepository.findByEmail(anyString())).thenReturn(null);

        String result = userRegisterService.findUserByEmail("pesho@gmail.com");
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserFound_ShouldReturnEmail() {
        UserEntity user = new UserEntity();
        user.setEmail("ivo@gmail.com");

        when(userCommandRepository.findByEmail("ivo@gmail.com")).thenReturn(user);

        String result = userRegisterService.findUserByEmail("ivo@gmail.com");
        assertEquals("ivo@gmail.com", result);
    }
}
