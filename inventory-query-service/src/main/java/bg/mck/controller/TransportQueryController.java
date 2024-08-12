//package bg.mck.controller;
//
//import bg.mck.dto.TransportDTO;
//import bg.mck.service.TransportQueryService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/${APPLICATION_VERSION}/user/inventory/query/transports")
//public class TransportQueryController {
//
//    private final TransportQueryService transportQueryService;
//
//    public TransportQueryController(TransportQueryService transportQueryService) {
//        this.transportQueryService = transportQueryService;
//    }
//
//
//    @Operation(summary = "Retrieve a transport by ID", description = "Fetches detailed information of a transport given its ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Transport details successfully retrieved",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransportDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Transport not found")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<TransportDTO> getTransportById(@PathVariable String id) {
//        TransportDTO transportDTO = transportQueryService.getTransportById(id);
//
//        if (transportDTO == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(transportDTO);
//    }
//
//    @Operation(summary = "Retrieve all transports", description = "Fetches detailed information of all transports")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Transports details successfully retrieved",
//                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransportDTO.class)))),
//            @ApiResponse(responseCode = "404", description = "No services found")
//    })
//    @GetMapping()
//    public ResponseEntity<List<TransportDTO>> getAllTransports() {
//        List<TransportDTO> transportDTOs = transportQueryService.getAllTransports();
//
//        if (transportDTOs.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(transportDTOs);
//    }
//
//
//    @Operation(summary = "Retrieve all transports given their part of the name", description = "Fetches detailed information of all transports given their part of the name")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Transports details successfully retrieved",
//                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransportDTO.class)))),
//    })
//    @GetMapping("/search")
//    public ResponseEntity<List<TransportDTO>> getTransportByPartOfName(@RequestParam("transportName") String transportName) {
//        List<TransportDTO> transportDTOs = this.transportQueryService.findTransportByName(transportName);
//        return ResponseEntity.ok(transportDTOs);
//    }
//}
