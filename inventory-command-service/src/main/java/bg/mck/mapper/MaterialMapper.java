package bg.mck.mapper;

import bg.mck.dto.*;
import bg.mck.entity.materialEntity.*;
import bg.mck.events.material.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    FastenerUpdateDTO mapFastenerDtoFromUpdateMaterialDTO(UpdateMaterialDTO updateMaterialDTO);
    InsulationUpdateDTO mapInsulationDtoFromUpdateMaterialDTO(UpdateMaterialDTO updateMaterialDTO);
    GalvanizedSheetUpdateDTO mapGalvanizedSheetDtoFromUpdateMaterialDTO(UpdateMaterialDTO updateMaterialDTO);
    PanelUpdateDTO mapPanelDtoFromUpdateMaterialDto(UpdateMaterialDTO updateMaterialDTO);
    RebarUpdateDTO mapRebarDtoFromUpdateMaterialDto(UpdateMaterialDTO updateMaterialDTO);
    SetUpdateDTO mapSetDtoFromUpdateMaterialDto(UpdateMaterialDTO updateMaterialDTO);
    UnspecifiedUpdateDTO mapUnspecifiedDtoFromUpdateMaterialDto(UpdateMaterialDTO updateMaterialDTO);
    MetalUpdateDTO mapMetalDtoFromUpdateMaterialDto(UpdateMaterialDTO updateMaterialDTO);

    void updateFastenerEntityFromDto(FastenerUpdateDTO fastenerUpdateDTO, @MappingTarget FastenerEntity fastenerEntity);
    void updateGalvanizedSheetEntityFromDto(GalvanizedSheetUpdateDTO galvanizedSheetUpdateDTO, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);
    void updateInsulationEntityFromDto(InsulationUpdateDTO insulationUpdateDTO, @MappingTarget InsulationEntity insulationEntity);
    void updatePanelEntityFromDto(PanelUpdateDTO panelUpdateDTO, @MappingTarget PanelEntity panelEntity);
    void updateRebarEntityFromDto(RebarUpdateDTO rebarUpdateDTO, @MappingTarget RebarEntity rebarEntity);
    void updateSetEntityFromDto(SetUpdateDTO setUpdateDTO,@MappingTarget SetEntity setEntity);
    void updateUnspecifiedEntityFromDto(UnspecifiedUpdateDTO unspecifiedUpdateDTO,@MappingTarget UnspecifiedEntity unspecifiedEntity);
    void updateMetalEntityFromDto(MetalUpdateDTO metalUpdateDTO,@MappingTarget MetalEntity metalEntity);
    @Mapping(target = "category",ignore = true)
    void mapFastenerEntityToEvent(FastenerEntity fastenerEntity, @MappingTarget FastenerUpdateEvent fastenerUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapInsulationEntityToEvent(InsulationEntity insulationEntity, @MappingTarget InsulationUpdateEvent insulationUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapGalvanizedSheetToEvent(GalvanisedSheetEntity galvanisedSheetEntity, @MappingTarget GalvanizedSheetUpdateEvent galvanizedSheetUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapPanelEntityToEvent(PanelEntity panelEntity, @MappingTarget PanelUpdateEvent panelUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapRebarEntityToEvent(RebarEntity rebarEntity, @MappingTarget RebarUpdateEvent rebarUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapSetEntityToEvent(SetEntity setEntity,@MappingTarget SetUpdateEvent setUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapUnspecifiedEntityToEvent(UnspecifiedEntity unspecifiedEntity,@MappingTarget UnspecifiedUpdateEvent unspecifiedUpdateEvent);
    @Mapping(target = "category",ignore = true)
    void mapMetalEntityToEvent(MetalEntity metalEntity,@MappingTarget MetalUpdateEvent metalUpdateEvent);
}
