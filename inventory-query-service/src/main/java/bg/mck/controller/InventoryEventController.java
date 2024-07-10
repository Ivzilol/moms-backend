package bg.mck.controller;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryEventController {

    private final EventService eventService;

    public InventoryEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/inventory/events")
     void processMaterialEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType,
                                                    @RequestHeader("Material-Type") String materialType) throws JsonProcessingException {
        System.out.println(data);
        eventService.processMaterialEvent(data, eventType, materialType);

    }


}
