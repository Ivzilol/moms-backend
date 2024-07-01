package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import bg.mck.userqueryservice.application.service.EventService;
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
public class UserEventController {

    private final EventService eventService;

    public UserEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Send User Event", description = "Processes a user event given the event data and event type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully processed"),
            @ApiResponse(responseCode = "400", description = "Invalid event type")
    })
    @PostMapping("/users/event")
    public ResponseEntity<Void> sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        eventService.processUserEvent(data, eventType);
        return ResponseEntity.ok().build();
    }
}
