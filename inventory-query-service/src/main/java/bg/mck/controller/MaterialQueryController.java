package bg.mck.controller;

import bg.mck.dto.MaterialDTO;
import bg.mck.service.MaterialQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import bg.mck.service.MaterialSearchService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaterialQueryController {

    private final MaterialSearchService materialSearchService;
    private final MaterialQueryService materialQueryService;

    public MaterialQueryController(MaterialQueryService materialQueryService, MaterialSearchService materialSearchService) {
        this.materialQueryService = materialQueryService;
        this.materialSearchService = materialSearchService;
    }

    @Operation(summary = "Retrieve material by category and ID", description = "Fetches detailed information of a material given its category and ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MaterialDTO.class))),
            @ApiResponse(responseCode = "404", description = "Material not found"),
            @ApiResponse(responseCode = "400", description = "Invalid Category")
    })
    @GetMapping("/${APPLICATION_VERSION}/admin/inventory/query/{category}/{id}")
    public ResponseEntity<MaterialDTO> getMaterialByCategoryAndId(@PathVariable String category, @PathVariable String id) {
        MaterialDTO materialDTO = materialQueryService.getMaterialByCategoryAndId(category, id);

        if (materialDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materialDTO);
    }


    @Operation(summary = "Retrieve all materials by category", description = "Fetches detailed information of all materials given their category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materials details successfully retrieved",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MaterialDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No materials found"),
            @ApiResponse(responseCode = "400", description = "Invalid Category")
    })
    @GetMapping("/${APPLICATION_VERSION}/admin/inventory/query/{category}")
    public ResponseEntity<List<MaterialDTO>> getAllMaterialsByCategory(@PathVariable String category) {
        List<MaterialDTO> materialDTOs = materialQueryService.getAllMaterialsByCategory(category);

        if (materialDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materialDTOs);
    }


    @GetMapping("/${APPLICATION_VERSION}/user/inventory/query/search")
    public ResponseEntity<?> getMaterialByPartOfName(@RequestParam("category") String category,
                                                     @RequestParam("materialName") String materialName) {
        List<? extends MaterialDTO> materialDTO = this.materialSearchService
                .findMaterialByCategoryAndName(category, materialName);
        return ResponseEntity.ok(materialDTO);
    }
}
