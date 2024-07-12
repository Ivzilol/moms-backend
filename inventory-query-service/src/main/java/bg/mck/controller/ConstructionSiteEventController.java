package bg.mck.controller;

import bg.mck.service.ConstructionSiteEventService;
import bg.mck.service.ServiceEventService;
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


    @PostMapping("/inventory/construction/events")
    public ResponseEntity<Void> processConstructionEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType){
        constructionEventService.processConstructionEvent(data, eventType);
        return ResponseEntity.ok().build();
    }


}
