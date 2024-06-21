package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import bg.mck.userqueryservice.application.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PostMapping("/users/event")
    public ResponseEntity<Void> sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        eventService.processUserEvent(data, eventType);
        return ResponseEntity.ok().build();
    }
}
