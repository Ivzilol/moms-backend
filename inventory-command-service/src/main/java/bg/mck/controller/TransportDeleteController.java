//package bg.mck.controller;
//
//import bg.mck.service.TransportDeleteService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/${APPLICATION_VERSION}/admin/inventory/command")
//public class TransportDeleteController {
//
//    private final TransportDeleteService transportDeleteService;
//
//    public TransportDeleteController(TransportDeleteService transportDeleteService) {
//        this.transportDeleteService = transportDeleteService;
//    }
//
//    @Operation(summary = "Delete Transport")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Successfully deleted transport"),
//            @ApiResponse(responseCode = "404", description = "Invalid transport ID",
//                    content = @Content(mediaType = "text/plain"))
//    })
//    @DeleteMapping("/transports/{id}")
//    public ResponseEntity<Void> deleteTransportById(@PathVariable Long id) {
//        transportDeleteService.deleteTransportById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
