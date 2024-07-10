package bg.mck.controller;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.dto.ErrorCreateMaterialDTO;
import bg.mck.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/inventory/command")
public class MaterialController {

    private final InventoryService inventoryService;

    public MaterialController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMaterial(@RequestBody @Valid CreateMaterialDTO createMaterialDTO,
                                            BindingResult result) {
        ResponseEntity<ErrorCreateMaterialDTO> errorCreateMaterialDTO =
                errorRegistrationMaterial(createMaterialDTO, result);
        if (errorCreateMaterialDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorCreateMaterialDTO.getBody());
        }
        this.inventoryService.createMaterial(createMaterialDTO);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<ErrorCreateMaterialDTO> errorRegistrationMaterial(CreateMaterialDTO createMaterialDTO, BindingResult result) {
        ErrorCreateMaterialDTO errorCreateMaterialDTO = new ErrorCreateMaterialDTO();
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(errorCreateMaterialDTO);
        }
        return null;
    }
}
