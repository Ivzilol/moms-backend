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

import static bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConstructionSiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ConstructionSiteDTO constructionSiteDTO;
    private ConstructionSiteEntity constructionSiteEntity;

    @MockBean
    private OrderQueryServiceClient orderQueryServiceClient;

    @Autowired
    private ConstructionSiteRepository constructionSiteRepository;


    @BeforeEach
    void setUp() {
        constructionSiteDTO = createConstructionSiteDTO();
        constructionSiteEntity = createConstructionSiteEntity();
    }

    @AfterEach
    void tearDown() {
        constructionSiteRepository.deleteAll();
        constructionSiteRepository.flush();
    }

    @Test
    void testCreateConstructionSite() throws Exception {
        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("1234"))
                .andExpect(jsonPath("$.name").value("Site Name"));
    }

    @Test
    void testCreateConstructionSite_AlreadyExistsNameAndNumber() throws Exception {
        constructionSiteRepository.save(constructionSiteEntity);

        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Construction site with name Site Name already exists"));
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
    void testCreateConstructionSite_MissingNumber() throws Exception {

        constructionSiteDTO.setConstructionNumber(null);

        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Construction site number must not be empty."));
    }

    @Test
    void testCreateConstructionSite_SuccessEventCreation() throws Exception {
        mockMvc.perform(post("/V1/admin/order/command/create-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("1234"))
                .andExpect(jsonPath("$.name").value("Site Name"));

        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_CREATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("Site Name");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("1234");
    }

    @Test
    public void testUpdateConstructionSite() throws Exception {
        constructionSiteRepository.save(constructionSiteEntity);

        constructionSiteDTO.setId(constructionSiteEntity.getId());
        constructionSiteDTO.setName("New Updated Site");
        constructionSiteDTO.setConstructionNumber("18.23.1");

        mockMvc.perform(patch("/V1/admin/order/command/update-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("18.23.1"))
                .andExpect(jsonPath("$.name").value("New Updated Site"));

        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_UPDATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("New Updated Site");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("18.23.1");

        assertThat(constructionSiteRepository.findAll()).hasSize(1);
        assertThat(constructionSiteRepository.findAll().get(0).getId()).isEqualTo(2L);
        assertThat(constructionSiteRepository.findAll().get(0).getName()).isEqualTo("New Updated Site");
        assertThat(constructionSiteRepository.findAll().get(0).getConstructionNumber()).isEqualTo("18.23.1");
    }

    @Test
    public void testUpdateConstructionSiteName() throws Exception {
        constructionSiteRepository.save(constructionSiteEntity);

        ConstructionSiteDTO constructionSiteDTOWithID = createConstructionSiteDTOWithID();
        constructionSiteDTOWithID.setId(constructionSiteEntity.getId());
        constructionSiteDTOWithID.setName("New Updated Site");

        mockMvc.perform(patch("/V1/admin/order/command/update-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTOWithID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("1234"))
                .andExpect(jsonPath("$.name").value("New Updated Site"));

        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_UPDATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("New Updated Site");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("1234");

        assertThat(constructionSiteRepository.findAll()).hasSize(1);
        assertThat(constructionSiteRepository.findAll().get(0).getId()).isEqualTo(5L);
        assertThat(constructionSiteRepository.findAll().get(0).getName()).isEqualTo("New Updated Site");
        assertThat(constructionSiteRepository.findAll().get(0).getConstructionNumber()).isEqualTo("1234");
    }

    @Test
    public void testUpdateConstructionSiteNumber() throws Exception {

        constructionSiteRepository.save(constructionSiteEntity);

        ConstructionSiteDTO constructionSiteDTO = createConstructionSiteDTOWithID();
        constructionSiteDTO.setConstructionNumber("12345.new");

        mockMvc.perform(patch("/V1/admin/order/command/update-construction-site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(constructionSiteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructionNumber").value("12345.new"))
                .andExpect(jsonPath("$.name").value("Site Name"));

        ArgumentCaptor<EventData<ConstructionSiteEvent>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> eventTypeCaptor = ArgumentCaptor.forClass(String.class);

        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(eventCaptor.capture(), eventTypeCaptor.capture());

        assertThat(eventTypeCaptor.getValue()).isEqualTo("CONSTRUCTION_SITE_UPDATED");
        assertThat(eventCaptor.getValue().getEvent().getId()).isNotNull();
        assertThat(eventCaptor.getValue().getEvent().getName()).isEqualTo("Site Name");
        assertThat(eventCaptor.getValue().getEvent().getConstructionNumber()).isEqualTo("12345.new");

        assertThat(constructionSiteRepository.findAll()).hasSize(1);
        assertThat(constructionSiteRepository.findAll().get(0).getId()).isEqualTo(1L);
        assertThat(constructionSiteRepository.findAll().get(0).getName()).isEqualTo("Site Name");
        assertThat(constructionSiteRepository.findAll().get(0).getConstructionNumber()).isEqualTo("12345.new");
    }






}
