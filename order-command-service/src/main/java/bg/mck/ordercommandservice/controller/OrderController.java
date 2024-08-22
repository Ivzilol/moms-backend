package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.FileDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;
import bg.mck.ordercommandservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/order/command")
public class OrderController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final OrderService orderService;
    private final RestTemplate restTemplate;


    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect data",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(value = "/create-order", consumes = {"multipart/form-data"})
    public ResponseEntity<OrderConfirmationDTO> createOrder(@RequestPart(value = "order") @Valid OrderDTO order,
                                                            @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws IOException {
        String email = extractEmailFromToken(token);

        List<FileDTO> fileUrls = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile multipartFile : files) {
                File file = convertMultipartFileToFile(multipartFile);

                FileSystemResource fileResource = new FileSystemResource(file);

                MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
                formData.add("order", order);
                formData.add("files", fileResource);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                headers.set(HttpHeaders.AUTHORIZATION, token);

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

                String fileStorageServiceUrl = "http://file-storage-service/" + APPLICATION_VERSION + "/user/files/upload";

                ResponseEntity<FileDTO> response = restTemplate.exchange(fileStorageServiceUrl, HttpMethod.POST, requestEntity, FileDTO.class);

                fileUrls.add(response.getBody());
            }
        }
        return ResponseEntity.ok(orderService.createOrder(order, email, fileUrls));
    }

    private static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }


    @Operation(summary = "Update order from user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order update successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect data",
                    content = {@Content(mediaType = "application/json")})
    })
    @PatchMapping(value = "/update-order", consumes = {"multipart/form-data"})
    public ResponseEntity<OrderConfirmationDTO> updateOrder(@RequestPart(value = "order") @Valid OrderDTO order,
                                                            @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String email = extractEmailFromToken(token);

        return ResponseEntity.ok(orderService.updateOrder(order, email, files));
    }


    @Operation(summary = "Cancel an order",
            description = "Cancels an order based on the provided order ID. The request must be authorized.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderConfirmationDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid order ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderNotFoundException.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderNotFoundException.class))})
    })
    @PatchMapping(value = "/delete-order/{orderId}")
    public ResponseEntity<OrderConfirmationDTO> deleteOrder(@PathVariable Long orderId,
                                                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String email = extractEmailFromToken(token);

        return ResponseEntity.ok(orderService.deleteOrder(orderId, email));
    }


    @Operation(summary = "Delete a material from an order",
            description = "Deletes a specific material from an order based on the provided order ID and material ID. The request must be authorized.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderConfirmationDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid order ID or material ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderNotFoundException.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Order or material not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderNotFoundException.class))})
    })
    @PatchMapping(value = "/delete-material/{orderId}/{materialId}")
    public ResponseEntity<OrderConfirmationDTO> deleteMaterial(@PathVariable Long orderId, @PathVariable Long materialId,
                                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String email = extractEmailFromToken(token);

        return ResponseEntity.ok(orderService.deleteMaterial(orderId, materialId, email));
    }


    private String extractEmailFromToken(String token) {
        token = token.substring(7);
        return restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);
    }
}
