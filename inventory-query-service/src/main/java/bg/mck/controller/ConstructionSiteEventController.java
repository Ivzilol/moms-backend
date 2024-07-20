package bg.mck.controller;

import bg.mck.service.ConstructionSiteEventService;
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
public class ConstructionSiteEventController {

    private final ConstructionSiteEventService constructionEventService;

    public ConstructionSiteEventController(ConstructionSiteEventService constructionEventService) {
        this.constructionEventService = constructionEventService;
    }

    @Operation(summary = "Process Construction Site Event", description = "Processes a construction site event given the event data and event type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully processed"),
            @ApiResponse(responseCode = "400", description = "Invalid event type", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Invalid construction site ID", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/inventory/constructions/events")
    public ResponseEntity<Void> processConstructionEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        constructionEventService.processConstructionEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
