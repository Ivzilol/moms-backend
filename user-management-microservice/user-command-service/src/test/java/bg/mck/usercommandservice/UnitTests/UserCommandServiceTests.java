package bg.mck.usercommandservice.UnitTests;

import bg.mck.usercommandservice.application.entity.UserCommandEntity;
import bg.mck.usercommandservice.application.repository.UserCommandRepository;
import bg.mck.usercommandservice.application.service.UserCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserCommandServiceTests {

    @Mock
    private UserCommandRepository userCommandRepository;

    @InjectMocks
    private UserCommandService userCommandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findUserByEmail_NullOrEmptyValue_ShouldReturnNull() {

        String result = userCommandService.findUserByEmail(null);
        assertNull(result);

        result = userCommandService.findUserByEmail("");
        assertNull(result);

        result = userCommandService.findUserByEmail("   ");
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserNotFound_ShouldReturnNull() {
        when(userCommandRepository.findByEmail(anyString())).thenReturn(null);

        String result = userCommandService.findUserByEmail("pesho@gmail.com");
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserFound_ShouldReturnEmail() {
        UserCommandEntity user = new UserCommandEntity();
        user.setEmail("ivo@gmail.com");

        when(userCommandRepository.findByEmail("ivo@gmail.com")).thenReturn(user);

        String result = userCommandService.findUserByEmail("ivo@gmail.com");
        assertEquals("ivo@gmail.com", result);
    }
}
