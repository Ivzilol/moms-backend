package bg.mck.controller;

import bg.mck.service.ServiceDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/command")
public class ServiceDeleteController {

    private final ServiceDeleteService serviceDeleteService;

    public ServiceDeleteController(ServiceDeleteService serviceDeleteService) {
        this.serviceDeleteService = serviceDeleteService;
    }

    @Operation(summary = "Delete Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted service"),
            @ApiResponse(responseCode = "404", description = "Invalid service ID",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Long id) {
        serviceDeleteService.deleteServiceById(id);
        return ResponseEntity.noContent().build();
    }
}
