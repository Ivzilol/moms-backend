package bg.mck.controller;

import bg.mck.service.TransportEventService;
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


    @PostMapping("/inventory/transport/events")
    public ResponseEntity<Void> processTransportEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType){
        transportEventService.processTransportEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
