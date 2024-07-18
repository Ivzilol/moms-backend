package bg.mck.controller;

import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.enums.MaterialType;
import bg.mck.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
        }
        return ResponseEntity.ok().build();
    }
}
