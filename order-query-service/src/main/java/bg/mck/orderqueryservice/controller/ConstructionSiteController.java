package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.service.ConstructionSiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${APPLICATION_VERSION}/user/order/query/construction")
public class ConstructionSiteController {

    private final ConstructionSiteService constructionSiteService;

    public ConstructionSiteController(ConstructionSiteService constructionSiteService) {
        this.constructionSiteService = constructionSiteService;
    }

    @Operation(summary = "Get a construction site by its name", description = "Fetches a construction site using its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved construction site"),
            @ApiResponse(responseCode = "404", description = "Construction site not found")
    })
    @GetMapping("/{constructionSiteName}")
    public ResponseEntity<ConstructionSiteDTO> getConstructionSiteByName(
            @Parameter(description = "Name of the construction site") @PathVariable String constructionSiteName) {
        return ResponseEntity.ok(constructionSiteService.getConstructionSiteByName(constructionSiteName));
    }

    @Operation(summary = "Get a construction site by its ID", description = "Fetches a construction site using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved construction site"),
            @ApiResponse(responseCode = "404", description = "Construction site not found")
    })
    @GetMapping("/{constructionSiteId}")
    public ResponseEntity<ConstructionSiteDTO> getConstructionSiteById(
            @Parameter(description = "ID of the construction site") @PathVariable String constructionSiteId) {
        return ResponseEntity.ok(constructionSiteService.getConstructionSiteById(constructionSiteId));
    }

    @Operation(summary = "Get all construction sites", description = "Fetches a list of all construction sites.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of construction sites")
    @GetMapping("/all")
    public ResponseEntity<List<ConstructionSiteDTO>> getAllConstructionSites() {
        return ResponseEntity.ok(constructionSiteService.getAllConstructionSites());
    }
}
