//package bg.mck.controller;
//
//import bg.mck.dto.ConstructionSiteDTO;
//import bg.mck.service.ConstructionQueryService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/${APPLICATION_VERSION}/user/inventory/query/constructions")
//public class ConstructionSiteQueryController {
//
//    private final ConstructionQueryService constructionSiteService;
//
//    public ConstructionSiteQueryController(ConstructionQueryService constructionSiteService) {
//        this.constructionSiteService = constructionSiteService;
//    }
//
//
//    @Operation(summary = "Retrieve a construction by ID", description = "Fetches detailed information of a construction site given its ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Construction site details successfully retrieved",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConstructionSiteDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Construction site not found")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<ConstructionSiteDTO> getConstructionById(@PathVariable String id) {
//        ConstructionSiteDTO constructionSiteDTO = constructionSiteService.getConstructionById(id);
//
//        if (constructionSiteDTO == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(constructionSiteDTO);
//    }
//
//    @Operation(summary = "Retrieve all construction sites", description = "Fetches detailed information of all construction sites")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Construction site details successfully retrieved",
//                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConstructionSiteDTO.class)))),
//            @ApiResponse(responseCode = "404", description = "No construction sites found found")
//    })
//    @GetMapping()
//    public ResponseEntity<List<ConstructionSiteDTO>> getAllConstructionSites() {
//        List<ConstructionSiteDTO> constructionDTOs = constructionSiteService.getAllConstructionSites();
//
//        if (constructionDTOs.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(constructionDTOs);
//    }
//
//
//    @Operation(summary = "Retrieve all construction sites given their part of the name", description = "Fetches detailed information of all construction sites given their part of the name")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Construction sites details successfully retrieved",
//                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConstructionSiteDTO.class)))),
//    })
//    @GetMapping("/search")
//    public ResponseEntity<List<ConstructionSiteDTO>> getConstructionByPartOfName(@RequestParam("constructionName") String constructionName) {
//        List<ConstructionSiteDTO> constructionDTOs = this.constructionSiteService.findConstructionByName(constructionName);
//        return ResponseEntity.ok(constructionDTOs);
//    }
//}
