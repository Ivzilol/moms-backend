package bg.mck.controller;

import bg.mck.dto.CreateMaterialDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/inventory")
public class InventoryController {


    @PostMapping("/create")
    public ResponseEntity<?> createMaterial(@RequestBody CreateMaterialDTO createMaterialDTO) {
        return null;
    }
}
