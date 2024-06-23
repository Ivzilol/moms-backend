package bg.mck.usercommandservice.UserIntegrationTests;

import bg.mck.usercommandservice.application.dto.UserChangePasswordDTO;
import bg.mck.usercommandservice.application.dto.UserRegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserPasswordControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl = "http://localhost";
    @LocalServerPort
    private int port;
    @BeforeEach
    public void setUp() throws Exception {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/users");

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("pesho@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Pesho");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("user");


        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterDTO)));
    }


    @Test
    public void testCurrentPasswordNull_shouldReturnBadRequest() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword(null);
        userChangePasswordDTO.setNewPassword("asdasd");
        userChangePasswordDTO.setConfirmPassword("asdasd");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNewPasswordNull_shouldReturnBadRequest() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("asdasd");
        userChangePasswordDTO.setNewPassword(null);
        userChangePasswordDTO.setConfirmPassword("asdasd");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testConfirmPasswordNull_shouldReturnBadRequest() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("asdasd");
        userChangePasswordDTO.setNewPassword("asdasd");
        userChangePasswordDTO.setConfirmPassword(null);

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPasswordsDoesntMatch_shouldReturnBadRequest() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("asdasd");
        userChangePasswordDTO.setNewPassword("asdasd");
        userChangePasswordDTO.setConfirmPassword("qweqwe");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPasswordUnder6Characters_shouldReturnBadRequest() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("asdasd");
        userChangePasswordDTO.setNewPassword("qwe");
        userChangePasswordDTO.setConfirmPassword("qwe");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNonExistingUser_shouldReturnNotFound() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("qweqwe");
        userChangePasswordDTO.setNewPassword("asdasd");
        userChangePasswordDTO.setConfirmPassword("asdasd");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testExistingUserChangePassword_shouldReturnStatusOk() throws Exception {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setCurrentPassword("qweqwe");
        userChangePasswordDTO.setNewPassword("asdasd");
        userChangePasswordDTO.setConfirmPassword("asdasd");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isOk());

        userChangePasswordDTO.setCurrentPassword("asdasd");
        userChangePasswordDTO.setNewPassword("qweqwe");
        userChangePasswordDTO.setConfirmPassword("qweqwe");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/change-password/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isOk());
    }
}
