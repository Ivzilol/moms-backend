package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.FileDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
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

import static bg.mck.ordercommandservice.testUtils.OrderUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;

    private OrderDTO validOrderDTO;
    private OrderConfirmationDTO orderConfirmationDTO;
    private String token;

    @BeforeEach
    void setUp() {
        validOrderDTO = createOrderDTO();
        orderConfirmationDTO = createOrderConfirmationDTO();
        token = "Bearer valid-token";
    }

    @Test
    void testCreateOrder_Success() throws Exception {
        String orderJson = objectMapper.writeValueAsString(validOrderDTO);
        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());

        FileDTO fileDTO = createFileDTO();
        String email = "test@example.com";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/user/order/command/create-order")
                .file(orderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, token);

        builder.with(request -> {
            request.setMethod("POST");
            return request;
        });

        Mockito.when(orderService.createOrder(Mockito.any(OrderDTO.class), Mockito.anyString(), Mockito.anyList()))
                .thenReturn(orderConfirmationDTO);

        mockMvc.perform(builder)
                .andExpect(status().isOk());

    }

    @Test
    void testCreateOrder_InvalidData() throws Exception {
        OrderDTO invalidOrderDTO = new OrderDTO();
        String orderJson = objectMapper.writeValueAsString(invalidOrderDTO);
        MockMultipartFile orderPart = new MockMultipartFile("order", "", "application/json", orderJson.getBytes());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/V1/user/order/command/create-order")
                .file(orderPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, token);

        builder.with(request -> {
            request.setMethod("POST");
            return request;
        });

        mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }
}
