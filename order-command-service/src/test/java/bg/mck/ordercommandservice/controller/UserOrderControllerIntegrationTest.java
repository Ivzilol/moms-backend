package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

import static bg.mck.ordercommandservice.testUtils.OrderUtil.createOrderDTO;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserOrderController.class)
public class UserOrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;

    private String token;

    @BeforeEach
    void setUp() {
        token = "Bearer valid-token";
    }

    @Test
    void testGetAnswerToAdminNote_Success() throws Exception {
        OrderDTO orderDTO = createOrderDTO();
        orderDTO.setId(1L);
        orderDTO.setDeliveryDate(ZonedDateTime.now().plusDays(1));

        ConstructionSiteDTO constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setId(1L);
        constructionSiteDTO.setName("Construction Site Name");
        constructionSiteDTO.setConstructionNumber("Construction Number");
        orderDTO.setConstructionSite(constructionSiteDTO);

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("Mocked FullName");
        when(orderService.addAnswerToAdminNote(Mockito.any(OrderDTO.class), Mockito.anyString())).thenReturn(orderDTO);

        String orderJson = objectMapper.writeValueAsString(orderDTO);
        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/user/order/command/answer-add")
                .file(orderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, token);

        builder.with(request -> {
            request.setMethod("PATCH");
            return request;
        });

        mockMvc.perform(builder)
                .andExpect(status().isOk());

        Mockito.verify(orderService).addAnswerToAdminNote(Mockito.any(OrderDTO.class), Mockito.anyString());
    }

    @Test
    void testGetAnswerToAdminNote_InvalidData() throws Exception {
        OrderDTO invalidOrderDTO = new OrderDTO();
        String orderJson = objectMapper.writeValueAsString(invalidOrderDTO);
        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/user/order/command/answer-add")
                .file(orderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, token);

        builder.with(request -> {
            request.setMethod("PATCH");
            return request;
        });

        mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

}
