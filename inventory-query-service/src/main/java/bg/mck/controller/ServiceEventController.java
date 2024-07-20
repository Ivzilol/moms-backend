package bg.mck.controller;

import bg.mck.service.ServiceEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceEventController {

    private final ServiceEventService serviceEventService;

    public ServiceEventController(ServiceEventService serviceEventService) {
        this.serviceEventService = serviceEventService;
    }

    @Operation(summary = "Process Service Event", description = "Processes a service event given the event data and event type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully processed"),
            @ApiResponse(responseCode = "400", description = "Invalid event type", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Invalid service ID", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/inventory/services/events")
    public ResponseEntity<Void> processServiceEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        serviceEventService.processServiceEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
