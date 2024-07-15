package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("${APPLICATION_VERSION}/")
public class GetOrderController {

    private final OrderService orderService;
    private final RestTemplate restTemplate;
    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;

    @Autowired
    public GetOrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }


    @GetMapping("admin/order/query/get-all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("user/order/query/get-my-orders")
    public ResponseEntity<List<OrderDTO>> getMyOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token ) {

        token = token.substring(7);
        String email = restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);
        return ResponseEntity.ok(orderService.getMyOrders(email));
    }
}
