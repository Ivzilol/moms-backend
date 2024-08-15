package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.service.OrderService;
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
public class UserOrderController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final RestTemplate restTemplate;
    private final OrderService orderService;

    public UserOrderController(RestTemplate restTemplate, OrderService orderService) {
        this.restTemplate = restTemplate;
        this.orderService = orderService;
    }


    @Operation(summary = "Answer to admin note from user account")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Answer added successfully"),
            @ApiResponse(responseCode = "400", description = "Incorrect data",
                    content = {@Content(mediaType = "multipart/form-data",
                            schema = @Schema(implementation = OrderDTO.class))}),
    }
    )
    @PatchMapping(value = "/answer-add", consumes = {"multipart/form-data"})
    public ResponseEntity<?> getAnswerToAdminNote(@RequestPart(value = "order") @Valid OrderDTO order,
                                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String email = extractEmailFromToken(token);
        String fullName = getFullNameFromToken(email);

        return ResponseEntity.ok(orderService.addAnswerToAdminNote(order, fullName));
    }

    private String getFullNameFromToken(String email) {
        return restTemplate
                .getForObject("http://user-query-service/" + APPLICATION_VERSION + "/user/user/query/get-fullname/" + email, String.class);
    }

    private String extractEmailFromToken(String token) {
        token = token.substring(7);
        return restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);
    }


}
