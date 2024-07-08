package bg.mck.controller;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryEventController {

    private final EventService eventService;

    public InventoryEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/inventory/events")
    <T extends BaseEvent> void processMaterialEvent(@RequestBody MaterialEvent<T> data, @RequestHeader("Event-Type") String eventType,
                                                    @RequestHeader("Material-Type") String materialType) {
        eventService.processMaterialEvent(data, eventType, materialType);
    }


}
