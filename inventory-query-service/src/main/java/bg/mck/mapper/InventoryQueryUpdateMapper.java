package bg.mck.mapper;

import bg.mck.entity.materialEntity.*;
import bg.mck.events.material.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryQueryUpdateMapper {
    InventoryQueryUpdateMapper INSTANCE = Mappers.getMapper(InventoryQueryUpdateMapper.class);
    void mapFastenerUpdateEventToFastenerEntity(UpdateFastenerEvent updateFastenerEvent, @MappingTarget FastenerEntity fastenerEntity);
    void mapUpdateGalvanizedSheetEventToGalvanizedSheetEntity(UpdateGalvanizedSheetEvent updateGalvanizedSheetEvent, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);
    void mapUpdateInsulationEventToInsulationEntity(UpdateInsulationEvent updateInsulationEvent, @MappingTarget InsulationEntity insulationEntity);
    void mapUpdateMetalEventToMetalEntity(UpdateMetalEvent updateMetalEvent, @MappingTarget MetalEntity metalEntity);
    void mapUpdatePanelEventToPanelEntity(UpdatePanelEvent updatePanelEvent, @MappingTarget PanelEntity panelEntity);
    void mapUpdateRebarEventToRebarEntity(UpdateRebarEvent updateRebarEvent, @MappingTarget RebarEntity rebarEntity);
    void mapUpdateSetEventToSetEntity(UpdateSetEvent updateSetEvent, @MappingTarget SetEntity setEntity);
    void mapUpdateUnspecifiedEventToUnspecifiedEntity(UpdateUnspecifiedEvent updateUnspecifiedEvent, @MappingTarget UnspecifiedEntity unspecifiedEntity);
}
