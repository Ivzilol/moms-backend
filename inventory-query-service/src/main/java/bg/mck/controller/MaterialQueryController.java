package bg.mck.controller;

import bg.mck.dto.FastenersDTO;
import bg.mck.service.MaterialQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/query")
public class MaterialQueryController {


    private final MaterialQueryService materialQueryService;

    public MaterialQueryController(MaterialQueryService materialQueryService) {
        this.materialQueryService = materialQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FastenersDTO> getFastenerById(@PathVariable Long id) {
        FastenersDTO fastenerDto = materialQueryService.getFastenerById(id);
        if (fastenerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fastenerDto);
    }

}
