package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.service.ConstructionSiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/order/command")
public class ConstructionSiteController {
    private final ConstructionSiteService constructionSiteService;

    public ConstructionSiteController(ConstructionSiteService constructionSiteService) {
        this.constructionSiteService = constructionSiteService;
    }

    @PostMapping("/create-construction-site")
    public ResponseEntity<ConstructionSiteDTO> createConstructionSite(@RequestBody @Valid ConstructionSiteDTO constructionSiteDTO) {
        return ResponseEntity.ok(constructionSiteService.createConstructionSite(constructionSiteDTO));
    }
}
