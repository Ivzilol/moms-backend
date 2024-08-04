package bg.mck.controller;

import bg.mck.dto.CreateConstructionDTO;
import bg.mck.service.ConstructionRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/inventory/command/constructions")
public class ConstructionRegisterController {

    private final ConstructionRegisterService constructionRegisterService;

    public ConstructionRegisterController(ConstructionRegisterService constructionRegisterService) {
        this.constructionRegisterService = constructionRegisterService;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createConstruction(@RequestBody @Valid CreateConstructionDTO createConstructionDTO) {
        constructionRegisterService.registerConstruction(createConstructionDTO);
        return ResponseEntity.ok().build();
    }
}
