package bg.mck.controller;

import bg.mck.service.ConstructionDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/command/constructions")
public class ConstructionDeleteController {

    private final ConstructionDeleteService constructionDeleteService;

    public ConstructionDeleteController(ConstructionDeleteService constructionDeleteService) {
        this.constructionDeleteService = constructionDeleteService;
    }


    @Operation(summary = "Delete Construction Site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted construction site"),
            @ApiResponse(responseCode = "404", description = "Invalid construction site ID",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConstructionSiteById(@PathVariable Long id) {
        constructionDeleteService.deleteConstructionSiteById(id);
        return ResponseEntity.noContent().build();
    }
}
