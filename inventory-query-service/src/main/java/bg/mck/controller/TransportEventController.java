package bg.mck.controller;

import bg.mck.service.TransportEventService;
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
public class TransportEventController {

    private final TransportEventService transportEventService;

    public TransportEventController(TransportEventService transportEventService) {
        this.transportEventService = transportEventService;
    }


    @Operation(summary = "Process Transport Event", description = "Processes a transport event given the event data and event type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully processed"),
            @ApiResponse(responseCode = "400", description = "Invalid event type", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Invalid transport ID", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/inventory/transports/events")
    public ResponseEntity<Void> processTransportEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        transportEventService.processTransportEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
