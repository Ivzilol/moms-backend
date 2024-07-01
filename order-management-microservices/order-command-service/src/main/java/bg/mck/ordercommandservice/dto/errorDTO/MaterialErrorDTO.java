package bg.mck.ordercommandservice.dto.errorDTO;

import bg.mck.ordercommandservice.dto.Material.*;
import bg.mck.ordercommandservice.entity.enums.MaterialType;

import java.util.Set;

public class MaterialErrorDTO {
    private MaterialType materialType;

    private Set<FastenerDTO> fasteners;
    private Set<GalvanisedSheetDTO> galvanisedSheets;
    private Set<InsulationDTO> insulation;
    private Set<MetalDTO> metals;
    private Set<PanelDTO> panels;
    private Set<RebarDTO> rebars;
    private Set<SetDTO> sets;
    private Set<UnspecifiedDTO> unspecified;
}
