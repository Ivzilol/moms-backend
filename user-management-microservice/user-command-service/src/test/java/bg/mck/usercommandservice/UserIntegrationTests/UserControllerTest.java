package bg.mck.usercommandservice.UserIntegrationTests;


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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
public class UserControllerTest {

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/users");

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("ivo_ali@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("USER");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)));
    }


    @Test
    public void userRegister_Successful_Test() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("ivo_ali3@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("USER");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void userRegister_EmailExist_Test() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("ivo_ali@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("USER");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailError")
                        .value("Email already exist"))
                .andReturn();
    }

    @Test
    public void userRegister_PasswordIsToShort_Test() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("ivo_ali3@abv.bg");
        userRegisterDTO.setPassword("asd");
        userRegisterDTO.setConfirmPassword("asd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("USER");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordError")
                        .value("The password must contain a minimum of 6 characters"))
                .andReturn();
    }

    @Test
    public void userRegister_NameIsEmpty_Test() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("ivo_ali3@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("");
        userRegisterDTO.setLastName("Georgiev");
        userRegisterDTO.setPhoneNumber("0888776655");
        userRegisterDTO.setRole("USER");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstNameError")
                        .value("First Name cannot be empty"))
                .andReturn();
    }
}
