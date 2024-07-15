package bg.mck.controller;

import bg.mck.service.MaterialDeleteService;
import bg.mck.service.ServiceDeleteService;
import bg.mck.service.TransportDeleteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/command")
public class DeleteInventoryItemController {


    private final MaterialDeleteService materialDeleteService;
    private final ServiceDeleteService serviceDeleteService;
    private final TransportDeleteService transportDeleteService;

    public DeleteInventoryItemController(MaterialDeleteService materialDeleteService, ServiceDeleteService serviceDeleteService, TransportDeleteService transportDeleteService) {
        this.materialDeleteService = materialDeleteService;
        this.serviceDeleteService = serviceDeleteService;
        this.transportDeleteService = transportDeleteService;
    }

    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterialByIdAndCategory(@PathVariable Long id, @RequestParam (name = "category") String categoryName) throws JsonProcessingException {
        materialDeleteService.deleteMaterialByIdAndCategory(id, categoryName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Long id) {
        serviceDeleteService.deleteServiceById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/transports/{id}")
    public ResponseEntity<Void> deleteTransportById(@PathVariable Long id) {
        transportDeleteService.deleteTransportById(id);
        return ResponseEntity.noContent().build();
    }



}
