package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(properties = "spring.config.import=optional:file:../../../../../../../../../application-env.properties")
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        userRepository.deleteAll();
        userRepository.save(getUser());
    }

    @Test
    public void testLoginShouldBeSuccessful() throws Exception {

        String loginJson = mapper.writeValueAsString(getLoginDTO());

        mockMvc.perform(post("/v1/user/query/login")
                .contentType("application/json")
                .content(loginJson))
                .andExpect(status().isOk());

    }

    @Test
    public void testLoginWithWrongEmailShouldFail() throws Exception {
        UserLoginDTO loginDTO = getLoginDTO();
        loginDTO.setEmail("test@test");

        String loginJson = mapper.writeValueAsString(loginDTO);

        mockMvc.perform(post("/v1/user/query/login")
                        .contentType("application/json")
                        .content(loginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid email or password"));
    }

    @Test
    public void testLoginWithWrongPasswordShouldFail() throws Exception {
        UserLoginDTO loginDTO = getLoginDTO();
        loginDTO.setPassword("testssss");

        String loginJson = mapper.writeValueAsString(loginDTO);

        mockMvc.perform(post("/v1/user/user/query/login")
                        .contentType("application/json")
                        .content(loginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid email or password"));
    }


    @AfterEach
    public void afterTest() throws Exception {
        userRepository.deleteAll();
    }


    private UserEntity getUser() {
        return new UserEntity.Builder()
                .setId("1")
                .setEmail("boroto_vr@abv.bg")
                .setPassword(BCrypt.hashpw("12345", BCrypt.gensalt()))
                .setRoles(Set.of("ADMIN", "USER"))
                .setPhoneNumber("0888888888")
                .setActive(true)
                .setFirstName("Petyo")
                .setLastName("Veselinov")
                .build();
    }

    private UserLoginDTO getLoginDTO() {
        return new UserLoginDTO("boroto_vr@abv.bg", "12345");
    }
}