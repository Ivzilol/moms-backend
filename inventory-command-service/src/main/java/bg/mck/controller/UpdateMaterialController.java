package bg.mck.controller;

import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.enums.MaterialType;
import bg.mck.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/inventory/command")
public class UpdateMaterialController {

    private final FastenersUpdateService fastenersUpdateService;
    private final GalvanizedSheetsUpdateService galvanizedSheetsUpdateService;
    private final InsulationUpdateService insulationUpdateService;
    private final PanelUpdateService panelUpdateService;
    private final RebarUpdateService rebarUpdateService;
    private final SetUpdateService setUpdateService;
    private final UnspecifiedUpdateService unspecifiedUpdateService;
    private final MetalUpdateService metalUpdateService;

    public UpdateMaterialController(FastenersUpdateService fastenersUpdateService, GalvanizedSheetsUpdateService galvanizedSheetsUpdateService, InsulationUpdateService insulationUpdateService, PanelUpdateService panelUpdateService, RebarUpdateService rebarUpdateService, SetUpdateService setUpdateService, UnspecifiedUpdateService unspecifiedUpdateService, MetalUpdateService metalUpdateService) {
        this.fastenersUpdateService = fastenersUpdateService;
        this.galvanizedSheetsUpdateService = galvanizedSheetsUpdateService;
        this.insulationUpdateService = insulationUpdateService;
        this.panelUpdateService = panelUpdateService;
        this.rebarUpdateService = rebarUpdateService;
        this.setUpdateService = setUpdateService;
        this.unspecifiedUpdateService = unspecifiedUpdateService;
        this.metalUpdateService = metalUpdateService;
    }
    @Operation(summary = "Update Material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated material"),
            @ApiResponse(responseCode = "404", description = "Invalid material ID or material type.",
                    content = @Content(mediaType = "text/plain"))
    })
    @PatchMapping("/update/{materialType}/{id}")
    public ResponseEntity<Void> updateMaterial(@PathVariable String materialType,
                                               @PathVariable Long id,
                                               @RequestBody UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        if (materialType.equalsIgnoreCase(MaterialType.FASTENERS.name())) {
            fastenersUpdateService.updateFastener(id, updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.GALVANIZED_SHEET.name())) {
            galvanizedSheetsUpdateService.updateGalvanizedSheet(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.INSULATION.name())) {
            insulationUpdateService.updateInsulation(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.PANELS.name())) {
            panelUpdateService.updatePanel(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.REBAR.name())) {
            rebarUpdateService.updateRebar(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.SET.name())) {
            setUpdateService.updateSet(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.UNSPECIFIED.name())) {
            unspecifiedUpdateService.updateUnspecified(id,updateMaterialDTO);
        } else if (materialType.equalsIgnoreCase(MaterialType.METAL.name())) {
            metalUpdateService.updateMetal(id,updateMaterialDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}