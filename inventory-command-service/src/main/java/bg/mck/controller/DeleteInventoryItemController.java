package bg.mck.controller;

import bg.mck.service.DeleteMaterialService;
import bg.mck.service.DeleteServiceService;
import bg.mck.service.DeleteTransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/inventory/command")
public class DeleteInventoryItemController {


    private final DeleteMaterialService deleteMaterialService;
    private final DeleteServiceService deleteServiceService;
    private final DeleteTransportService deleteTransportService;

    public DeleteInventoryItemController(DeleteMaterialService deleteMaterialService, DeleteServiceService deleteServiceService, DeleteTransportService deleteTransportService) {
        this.deleteMaterialService = deleteMaterialService;
        this.deleteServiceService = deleteServiceService;
        this.deleteTransportService = deleteTransportService;
    }

    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterialByIdAndCategory(@PathVariable Long id, @RequestParam (name = "category") String categoryName) {
        deleteMaterialService.deleteMaterialByIdAndCategory(id, categoryName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Long id) {
        deleteServiceService.deleteServiceById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/transports/{id}")
    public ResponseEntity<Void> deleteTransportById(@PathVariable Long id) {
        deleteTransportService.deleteTransportById(id);
        return ResponseEntity.noContent().build();
    }



}
