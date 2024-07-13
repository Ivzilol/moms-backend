package bg.mck.controller;

import bg.mck.dto.MaterialDTO;
import bg.mck.service.MaterialSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/query")
public class MaterialQueryController {

    private final MaterialSearchService materialSearchService;


    public MaterialQueryController(MaterialSearchService materialSearchService) {
        this.materialSearchService = materialSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getMaterialByPartOfName(@RequestParam("category") String category,
                                                     @RequestParam("materialName") String materialName) {
        List<MaterialDTO> materialDTO = this.materialSearchService
                .findMaterialByCategoryAndName(category, materialName);
        return ResponseEntity.ok(materialDTO);
    }
}
