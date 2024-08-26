package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static bg.mck.ordercommandservice.testUtils.OrderUtil.createOrderConfirmationDTO;
import static bg.mck.ordercommandservice.testUtils.OrderUtil.createOrderDTO;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminOrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private OrderService orderService;

    private final String authToken = "Bearer your_mocked_token";



    @Test
    void updateOrderStatus_Success() throws Exception {
        // Arrange
        OrderDTO orderDTO = createOrderDTO();
        orderDTO.setId(1L);
        ConstructionSiteDTO constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setId(1L);
        constructionSiteDTO.setName("Construction Site Name");
        constructionSiteDTO.setConstructionNumber("Construction Number");
        orderDTO.setConstructionSite(constructionSiteDTO);

        OrderConfirmationDTO orderConfirmationDTO = createOrderConfirmationDTO();
        when(orderService.updateOrderStatus(eq(orderDTO), anyString())).thenReturn(orderConfirmationDTO);
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("Mocked FullName");

        String orderJson = objectMapper.writeValueAsString(orderDTO);
        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/admin/order/command/update-order")
                .file(orderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, authToken);

        builder.with(request -> {
            request.setMethod("PATCH");
            return request;
        });

        mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderStatus_BadRequest() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();

        String orderJson = objectMapper.writeValueAsString(orderDTO);
        String updateOrderJson = objectMapper.writeValueAsString(updateOrderDTO);

        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());
        MockMultipartFile updateOrderPart = new MockMultipartFile("updateOrder", "", "application/json", updateOrderJson.getBytes());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/admin/order/command/update-order")
                .file(orderPart)
                .file(updateOrderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, authToken); // Add Authorization header

        builder.with(request -> {
            request.setMethod("PATCH"); // Override POST to PATCH
            return request;
        });

        mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }
}


