package bg.mck.controller;

import bg.mck.dto.ServiceDTO;
import bg.mck.service.ServiceQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/query/services")
public class ServiceQueryController {

    private final ServiceQueryService serviceQueryService;

    public ServiceQueryController(ServiceQueryService serviceQueryService) {
        this.serviceQueryService = serviceQueryService;
    }

    @Operation(summary = "Retrieve a service by ID", description = "Fetches detailed information of a service given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Service not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable String id) {
        ServiceDTO serviceDTO = serviceQueryService.getServiceById(id);

        if (serviceDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serviceDTO);
    }

    @Operation(summary = "Retrieve all service", description = "Fetches detailed information of all services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services details successfully retrieved",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No services found")
    })
    @GetMapping()
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> serviceDTOs = serviceQueryService.getAllServices();

        if (serviceDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serviceDTOs);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ServiceDTO>> getServiceByPartOfName(@RequestParam("serviceName") String serviceName) {
        List<ServiceDTO> serviceDTOs = this.serviceQueryService.findServiceByName(serviceName);
        return ResponseEntity.ok(serviceDTOs);
    }
}
