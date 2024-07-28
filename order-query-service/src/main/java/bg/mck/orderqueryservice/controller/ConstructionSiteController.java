package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.service.ConstructionSiteService;
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

    @GetMapping("/{constructionSiteName}")
    public ResponseEntity<ConstructionSiteDTO> getConstructionSiteByName(@PathVariable String constructionSiteName) {
        return ResponseEntity.ok(constructionSiteService.getConstructionSiteByName(constructionSiteName));
    }

    @GetMapping("/{constructionSiteId}")
    public ResponseEntity<ConstructionSiteDTO> getConstructionSiteById(@PathVariable String constructionSiteId) {
        return ResponseEntity.ok(constructionSiteService.getConstructionSiteById(constructionSiteId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ConstructionSiteDTO>> getAllConstructionSites() {
        return ResponseEntity.ok(constructionSiteService.getAllConstructionSites());
    }
}
