package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConstructionSiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ConstructionSiteDTO constructionSiteDTO;

    @BeforeEach
    void setUp() {
        constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setConstructionNumber("12345");
        constructionSiteDTO.setName("New Site");
    }

    @Test
    void testCreateConstructionSite() throws Exception {
        mockMvc.perform(post("/v1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("12345"))
                .andExpect(jsonPath("$.name").value("New Site"));
    }

    @Test
    void testCreateConstructionSiteAlreadyExists() throws Exception {
        // First creation should succeed
        mockMvc.perform(post("/${APPLICATION_VERSION}/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk());

        // Second creation with the same data should fail
        mockMvc.perform(post("/v1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isConflict());
    }


}
