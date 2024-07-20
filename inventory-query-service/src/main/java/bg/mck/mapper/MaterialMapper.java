package bg.mck.mapper;

import bg.mck.dto.*;
import bg.mck.entity.materialEntity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    FastenerEntity toFastenerEntity(FastenersDTO fastenersDTO);
    FastenersDTO toFastenerDTO(FastenerEntity fastenerEntity);

    GalvanisedSheetEntity toGalvanizedEntity(GalvanisedDTO galvanisedDTO);
    GalvanisedDTO toGalvanizedDTO(GalvanisedSheetEntity galvanisedEntity);

    InsulationEntity toInsulationEntity(InsulationDTO insulationDTO);
    InsulationDTO toInsulationDTO(InsulationEntity insulationEntity);

    MetalEntity toMetalEntity(MetalDTO metalDTO);
    MetalDTO toMetalDTO(MetalEntity metalEntity);

    PanelEntity toPanelEntity(PanelsDTO panelsDTO);
    PanelsDTO toPanelDTO(PanelEntity panelEntity);

    RebarEntity toRebarEntity(RebarDTO rebarDTO);
    RebarDTO toRebarDTO(RebarEntity rebarEntity);

    SetEntity toSetEntity(SetDTO setDTO);
    SetDTO toSetDTO(SetEntity setEntity);

    UnspecifiedEntity toUnspecifiedEntity(UnspecifiedDTO unspecifiedDTO);
    UnspecifiedDTO toUnspecifiedDTO(UnspecifiedEntity unspecifiedEntity);



}
