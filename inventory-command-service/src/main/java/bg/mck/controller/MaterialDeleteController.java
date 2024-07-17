package bg.mck.controller;

import bg.mck.dto.ErrorCreateMaterialDTO;
import bg.mck.service.MaterialDeleteService;
import bg.mck.service.ServiceDeleteService;
import bg.mck.service.TransportDeleteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/command")
public class MaterialDeleteController {


    private final MaterialDeleteService materialDeleteService;

    public MaterialDeleteController(MaterialDeleteService materialDeleteService) {
        this.materialDeleteService = materialDeleteService;
    }


    @Operation(summary = "Delete Material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted material"),
            @ApiResponse(responseCode = "404", description = "Invalid category or material ID",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterialByIdAndCategory(@PathVariable Long id, @RequestParam (name = "category") String categoryName) throws JsonProcessingException {
        materialDeleteService.deleteMaterialByIdAndCategory(id, categoryName);
        return ResponseEntity.noContent().build();
    }







}
