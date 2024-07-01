package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.exceptions.InvalidEmailOrPasswordException;
import bg.mck.userqueryservice.application.mapper.UserMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserLoginServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserLoginService userLoginService;

    private UserEntity user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new UserEntity.Builder()
                .setActive(true)
                .setEmail("boroto_vr@abv.bg")
                .setFirstName("Petyo")
                .setLastName("Veselinov")
                .setId("1")
                .setPassword(BCrypt.hashpw("proba", BCrypt.gensalt()))
                .setPhoneNumber("0888888888")
                .setRoles(Set.of("ADMIN", "USER"))
                .build();
    }


    @Test
    public void loginIncorrectEmailShouldThrowExceptionInvalidEmailOrPasswordException() {
        UserLoginDTO userLoginDTO = getUserLoginDTO();
        userLoginDTO.setEmail("bo");

        when(userRepository.findByEmail(userLoginDTO.getEmail())).thenReturn(null);

        InvalidEmailOrPasswordException exception = assertThrows(InvalidEmailOrPasswordException.class, () -> userLoginService.login(userLoginDTO));
        assertEquals("Invalid email or password", exception.getMessage());

    }

    @Test
    public void loginIncorrectPasswordShouldThrowExceptionInvalidEmailOrPasswordException() {
        UserLoginDTO userLoginDTO = getUserLoginDTO();
        userLoginDTO.setPassword("test");

        when(userRepository.findByEmail(userLoginDTO.getEmail())).thenReturn(user);

        InvalidEmailOrPasswordException exception = assertThrows(InvalidEmailOrPasswordException.class, () -> userLoginService.login(userLoginDTO));
        assertEquals("Invalid email or password", exception.getMessage());

    }

    @Test
    public void loginCorrectEmailAndPasswordShouldReturnUserLoginResponseDTO() {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO(user.getId(), user.getEmail(), user.getRoles());
        UserLoginDTO userLoginDTO = getUserLoginDTO();

        when(userRepository.findByEmail(userLoginDTO.getEmail())).thenReturn(user);
        when(userMapper.toUserResponseDTO(user)).thenReturn(userLoginResponseDTO);

        assertEquals(userLoginResponseDTO, userLoginService.login(userLoginDTO));
    }


    private UserLoginDTO getUserLoginDTO() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("boroto_vr@abv.bg");
        userLoginDTO.setPassword("proba");
        return userLoginDTO;
    }
}

