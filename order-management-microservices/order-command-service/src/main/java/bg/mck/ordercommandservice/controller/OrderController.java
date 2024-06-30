package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.UserDetailsDTO;
import bg.mck.ordercommandservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/order/command")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDTO order, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

//        UserDetailsDTO userDetailsDTO = orderService.getUserDetails(token); request to get the data from the token
        UserDetailsDTO tempmockUserDetailsDTO = new UserDetailsDTO(9L, "9test@gmail.com", "Gosho", "Goshev",
                "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ijl0ZXN0QGdtYWlsLmNvbSIsInJvbGVzIjoidXNlciIsImV4cGlyYXRpb24iOiIyNTkyMDAwMDAwIiwiaWQiOiI5Iiwic3ViIjoiOXRlc3RAZ21haWwuY29tIiwiaWF0IjoxNzE5MzgxMzEwLCJleHAiOjE3MjE5NzMzMTB9.FR0w7SgMeWmkHBjvbmp_4SrrG3EZN6HbvNEYMTy-X7Q");
        return ResponseEntity.ok(orderService.createOrder(order, tempmockUserDetailsDTO));
    }

}
