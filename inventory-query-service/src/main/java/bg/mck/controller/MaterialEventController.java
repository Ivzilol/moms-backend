package bg.mck.controller;

import bg.mck.service.MaterialEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialEventController {

    private final MaterialEventService materialEventService;

    public MaterialEventController(MaterialEventService materialEventService) {
        this.materialEventService = materialEventService;
    }

    @PostMapping("/inventory/materials/events")
    public ResponseEntity<Void> processMaterialEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType,
                                                     @RequestHeader("Material-Type") String materialType) throws JsonProcessingException {
        materialEventService.processMaterialEvent(data, eventType, materialType);
        return ResponseEntity.ok().build();
    }


}
