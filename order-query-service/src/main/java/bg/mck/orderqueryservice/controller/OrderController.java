package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("${APPLICATION_VERSION}/")
public class OrderController {

    private final OrderService orderService;
    private final RestTemplate restTemplate;
    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;

    @Autowired
    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Getting all orders from admin account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve all orders"),
    })
    @GetMapping("admin/order/query/get-all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Getting all orders from user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve all orders"),
    })
    @GetMapping("user/order/query/get-all")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUser() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Get order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("admin/order/query/get-order/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get order by Order Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/user/order/query/get-order-by-orderNumber/{number}")
    public ResponseEntity<OrderDTO> getOrderByOrderNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(orderService.getOrderByOrderNumber(number));
    }

    @Operation(summary = "Get all orders of the authenticated user",
            description = "Fetch all orders associated with the authenticated user's email retrieved from the authentication service.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user orders",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token")
    })
    @GetMapping("user/order/query/get-my-orders")
    public ResponseEntity<List<OrderDTO>> getMyOrders(@Parameter(description = "JWT token to authenticate the user", required = true)
                                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        token = token.substring(7);
        String email = restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);
        return ResponseEntity.ok(orderService.getMyOrders(email));
    }
}