package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.service.OrderService;
import bg.mck.ordercommandservice.service.UpdateOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/order/command")
public class OrderController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final OrderService orderService;
    private final RestTemplate restTemplate;

    private final UpdateOrderService updateOrderService;

    public OrderController(OrderService orderService, RestTemplate restTemplate, UpdateOrderService updateOrderService) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
        this.updateOrderService = updateOrderService;
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @Operation(summary = "Create order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Incorrect data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateOrderDTO.class))})
    }
    )
    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderDTO> createOrder(@Valid @RequestBody OrderDTO order, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        token = token.substring(7);
        String email = restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);

        return ResponseEntity.ok(orderService.createOrder(order, email));
    }

    @Operation(summary = "Update order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Order update successfully"),
            @ApiResponse(responseCode = "400", description = "Incorrect data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateOrderDTO.class))})
    }
    )
    @PatchMapping("/update-order")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody UpdateOrderDTO updateOrderDTO,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        this.updateOrderService.updateOrder(updateOrderDTO, token);
        return ResponseEntity.ok().build();
    }
}
