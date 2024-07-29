package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.service.ConstructionSiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/order/command")
public class ConstructionSiteController {
    private final ConstructionSiteService constructionSiteService;

    public ConstructionSiteController(ConstructionSiteService constructionSiteService) {
        this.constructionSiteService = constructionSiteService;
    }


    @Operation(summary = "Create construction site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Incorrect data"),
            @ApiResponse(responseCode = "409", description = "Construction site already exists")
    }
    )
    @PostMapping("/create-construction-site")
    public ResponseEntity<?> createConstructionSite(@RequestBody @Valid ConstructionSiteDTO constructionSiteDTO) {
        return ResponseEntity.ok(constructionSiteService.createConstructionSite(constructionSiteDTO));
    }

    @Operation(summary = "Update construction site")
    @PatchMapping("/update-construction-site")
    public ResponseEntity<?> updateConstructionSite(@RequestBody @Valid ConstructionSiteDTO constructionSiteDTO) {
        return ResponseEntity.ok(constructionSiteService.updateConstructionSite(constructionSiteDTO));
    }
}
