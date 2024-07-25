package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.dto.UpdateOrderDTO;
import bg.mck.orderqueryservice.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/orders/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping
    public ResponseEntity<Void> sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException, InvocationTargetException, IllegalAccessException {
        eventService.processOrderEvent(data, eventType);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/update")
//    public ResponseEntity<?> updateEvent(@RequestBody UpdateOrderDTO updateOrderDTO, @RequestHeader("Event-Type") String eventType) {
//        eventService.updateOrder(updateOrderDTO, eventType);
//        return ResponseEntity.ok().build();
//    }
}
