package bg.mck.controller;

import bg.mck.service.ServiceEventService;
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

    @PostMapping("/inventory/service/events")
    public ResponseEntity<Void> processServiceEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType){
        serviceEventService.processMaterialEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
