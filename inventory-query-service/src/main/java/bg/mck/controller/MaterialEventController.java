package bg.mck.controller;

import bg.mck.service.MaterialEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialEventController {

    private final MaterialEventService materialEventService;

    public MaterialEventController(MaterialEventService materialEventService) {
        this.materialEventService = materialEventService;
    }


    @Operation(summary = "Process Material Event", description = "Processes a material event given the event data, event type and material type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully processed"),
            @ApiResponse(responseCode = "400", description = "Invalid event type", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Invalid material type or material ID", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/inventory/materials/events")
    public ResponseEntity<Void> processMaterialEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType,
                                                     @RequestHeader("Material-Type") String materialType) throws JsonProcessingException {
        materialEventService.processMaterialEvent(data, eventType, materialType);
        return ResponseEntity.ok().build();
    }


}
