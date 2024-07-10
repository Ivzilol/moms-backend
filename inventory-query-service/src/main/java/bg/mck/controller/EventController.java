package bg.mck.controller;

import bg.mck.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventService createMaterialService;

    public EventController(EventService createMaterialService) {
        this.createMaterialService = createMaterialService;
    }

    @PostMapping("/inventory/event")
    public ResponseEntity<Void> sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        this.createMaterialService.createMaterial(data, eventType);
        return ResponseEntity.ok().build();
    }
}
