package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.event.ConstructionSiteEvent;
import bg.mck.ordercommandservice.event.EventData;
import bg.mck.ordercommandservice.repository.ConstructionSiteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    @MockBean
    private OrderQueryServiceClient orderQueryServiceClient;

    @Autowired
    private ConstructionSiteRepository constructionSiteRepository;


    @BeforeEach
    void setUp() {
        constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setConstructionNumber("12345");
        constructionSiteDTO.setName("New Site");
        constructionSiteDTO.setId(null);
    }

    @AfterEach
    void tearDown() {
        constructionSiteRepository.deleteAll();
    }

    @Test
    void testCreateConstructionSite() throws Exception {
        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("12345"))
                .andExpect(jsonPath("$.name").value("New Site"));
    }

    @Test
    void testCreateConstructionSite_AlreadyExistsNameAndNumber() throws Exception {

        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setConstructionNumber("12345");
        constructionSiteEntity.setName("New Site");
        constructionSiteRepository.save(constructionSiteEntity);

        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void testCreateConstructionSite_MissingName() throws Exception {

        constructionSiteDTO.setName(null);

        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("name").value("Construction site name must not be empty."));
    }

    @Test
    void testCreateConstructionSite_SuccessEventCreation() throws Exception {
        // When
        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("12345"))
                .andExpect(jsonPath("$.name").value("New Site"));

        // Then
        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        // Verify that the sendConstructionSiteEvent method was called
        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        // Optionally, you can add more assertions to check the content of the captured arguments
        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_CREATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("New Site");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("12345");
    }

    @Test
    public void testUpdateConstructionSite() throws Exception {
        // Given
        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setConstructionNumber("123123");
        constructionSiteEntity.setName("New Site123");
        constructionSiteRepository.save(constructionSiteEntity);

        ConstructionSiteDTO constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setConstructionNumber("18.23.1");
        constructionSiteDTO.setName("New Updated Site");
        constructionSiteDTO.setId(1L);

        // When
        mockMvc.perform(patch("/V1/admin/order/command/update-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("18.23.1"))
                .andExpect(jsonPath("$.name").value("New Updated Site"));

        // Then
        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        // Verify that the sendConstructionSiteEvent method was called
        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        // Optionally, you can add more assertions to check the content of the captured arguments
        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_UPDATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("New Updated Site");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("18.23.1");


        // Then
        assertThat(constructionSiteRepository.findAll()).hasSize(1);
        assertThat(constructionSiteRepository.findAll().get(0).getId()).isEqualTo(1L);
        assertThat(constructionSiteRepository.findAll().get(0).getName()).isEqualTo("New Updated Site");
        assertThat(constructionSiteRepository.findAll().get(0).getConstructionNumber()).isEqualTo("18.23.1");
    }

    @Test
    void testUpdateConstructionSite_AlreadyExists() throws Exception {

        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setConstructionNumber("12345");
        constructionSiteEntity.setName("New Site");
        constructionSiteRepository.save(constructionSiteEntity);

        mockMvc.perform(patch("/V1/admin/order/command/update-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isConflict());
    }


}
