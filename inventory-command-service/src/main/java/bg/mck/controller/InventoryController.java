package bg.mck.controller;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/inventory/command")
public class InventoryController {

//    private final InventoryService inventoryService;
//
//    public InventoryController(InventoryService inventoryService) {
//        this.inventoryService = inventoryService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createMaterial(@RequestBody CreateMaterialDTO createMaterialDTO) {
//        this.inventoryService.createMaterial(createMaterialDTO);
//        return ResponseEntity.ok().build();
//    }
}
