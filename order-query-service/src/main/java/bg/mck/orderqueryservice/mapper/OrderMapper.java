package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.dto.*;
import bg.mck.orderqueryservice.entity.*;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = MaterialType.class)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "fasteners", expression = "java(mapFasteners(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "galvanisedSheets", expression = "java(mapGalvanisedSheets(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "insulation", expression = "java(mapInsulation(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "metals", expression = "java(mapMetals(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "panels", expression = "java(mapPanels(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "rebars", expression = "java(mapRebars(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "sets", expression = "java(mapSets(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "unspecified", expression = "java(mapUnspecified(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "services", expression = "java(mapServices(event.getMaterials(), event.getMaterialType()))")
    @Mapping(target = "transports", expression = "java(mapTransports(event.getMaterials(), event.getMaterialType()))")
    OrderEntity toOrderEntity(CreateOrderEvent<?> event);

    default Set<FastenerEntity> mapFasteners(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.FASTENERS && materials != null) {
            return materials.stream()
                    .filter(FasterEvent.class::isInstance)
                    .map(FasterEvent.class::cast)
                    .map(this::toFasterEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<GalvanisedSheetEntity> mapGalvanisedSheets(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.GALVANIZED_SHEET && materials != null) {
            return materials.stream()
                    .filter(GalvanisedSheetEvent.class::isInstance)
                    .map(GalvanisedSheetEvent.class::cast)
                    .map(this::toGalvanisedSheetEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<InsulationEntity> mapInsulation(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.INSULATION && materials != null) {
            return materials.stream()
                    .filter(InsulationEvent.class::isInstance)
                    .map(InsulationEvent.class::cast)
                    .map(this::toInsulationEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<MetalEntity> mapMetals(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.METAL && materials != null) {
            return materials.stream()
                    .filter(MetalEvent.class::isInstance)
                    .map(MetalEvent.class::cast)
                    .map(this::toMetalEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<PanelEntity> mapPanels(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.PANELS && materials != null) {
            return materials.stream()
                    .filter(PanelEvent.class::isInstance)
                    .map(PanelEvent.class::cast)
                    .map(this::toPanelEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<RebarEntity> mapRebars(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.REBAR && materials != null) {
            return materials.stream()
                    .filter(RebarEvent.class::isInstance)
                    .map(RebarEvent.class::cast)
                    .map(this::toRebarEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<SetEntity> mapSets(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.SET && materials != null) {
            return materials.stream()
                    .filter(SetEvent.class::isInstance)
                    .map(SetEvent.class::cast)
                    .map(this::toSetEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<UnspecifiedEntity> mapUnspecified(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.UNSPECIFIED && materials != null) {
            return materials.stream()
                    .filter(UnspecifiedEvent.class::isInstance)
                    .map(UnspecifiedEvent.class::cast)
                    .map(this::toUnspecifiedEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<ServiceEntity> mapServices(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.SERVICE && materials != null) {
            return materials.stream()
                    .filter(ServiceEvent.class::isInstance)
                    .map(ServiceEvent.class::cast)
                    .map(this::toServiceEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<TransportEntity> mapTransports(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.TRANSPORT && materials != null) {
            return materials.stream()
                    .filter(TransportEvent.class::isInstance)
                    .map(TransportEvent.class::cast)
                    .map(this::toTransportEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    GalvanisedSheetEntity toGalvanisedSheetEntity(GalvanisedSheetEvent event);

    InsulationEntity toInsulationEntity(InsulationEvent event);

    MetalEntity toMetalEntity(MetalEvent event);

    PanelEntity toPanelEntity(PanelEvent event);

    RebarEntity toRebarEntity(RebarEvent event);

    SetEntity toSetEntity(SetEvent event);

    FastenerEntity toFasterEntity(FasterEvent event);

    UnspecifiedEntity toUnspecifiedEntity(UnspecifiedEvent event);

    ServiceEntity toServiceEntity(ServiceEvent event);

    TransportEntity toTransportEntity(TransportEvent event);

    OrderDTO fromOrderEntityToDTO(OrderEntity orderEntity);

    @Mapping(target = "length", expression = "java(fastenerEntity.getLength() != null ? fastenerEntity.getLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "lengthUnit", expression = "java(fastenerEntity.getLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(fastenerEntity.getLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    FastenerDTO fromFastenerEntityToDTO(FastenerEntity fastenerEntity);


    @Mapping(target = "maxLength", expression = "java(galvanisedSheetEntity.getMaxLength() != null ? galvanisedSheetEntity.getMaxLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "maxLengthUnit", expression = "java(galvanisedSheetEntity.getMaxLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(galvanisedSheetEntity.getMaxLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "area", expression = "java(galvanisedSheetEntity.getArea() != null ? galvanisedSheetEntity.getArea().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "areaUnit", expression = "java(galvanisedSheetEntity.getArea().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.AreaUnits.valueOf(galvanisedSheetEntity.getArea().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    GalvanisedSheetDTO fromGalvanisedSheetEntityToDTO(GalvanisedSheetEntity galvanisedSheetEntity);

    @Mapping(target = "thickness", expression = "java(insulationEntity.getThickness() != null ? insulationEntity.getThickness().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "thicknessUnit", expression = "java(insulationEntity.getThickness().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(insulationEntity.getThickness().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    InsulationDTO fromInsulationEntityToDTO(InsulationEntity insulationEntity);

    @Mapping(target = "totalWeight", expression = "java(metalEntity.getTotalWeight() != null ? metalEntity.getTotalWeight().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "totalWeightUnit", expression = "java(metalEntity.getTotalWeight().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.WeightUnits.valueOf(metalEntity.getTotalWeight().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    MetalDTO fromMetalEntityToDTO(MetalEntity metalEntity);

    @Mapping(target = "length", expression = "java(panelEntity.getLength() != null ? panelEntity.getLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "lengthUnit", expression = "java(panelEntity.getLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(panelEntity.getLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "width", expression = "java(panelEntity.getWidth() != null ? panelEntity.getWidth().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "widthUnit", expression = "java(panelEntity.getWidth().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(panelEntity.getWidth().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "totalThickness", expression = "java(panelEntity.getTotalThickness() != null ? panelEntity.getTotalThickness().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "totalThicknessUnit", expression = "java(panelEntity.getTotalThickness().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(panelEntity.getTotalThickness().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "frontSheetThickness", expression = "java(panelEntity.getFrontSheetThickness() != null ? panelEntity.getFrontSheetThickness().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "frontSheetThicknessUnit", expression = "java(panelEntity.getFrontSheetThickness().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(panelEntity.getFrontSheetThickness().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "backSheetThickness", expression = "java(panelEntity.getBackSheetThickness() != null ? panelEntity.getBackSheetThickness().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "backSheetThicknessUnit", expression = "java(panelEntity.getBackSheetThickness().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(panelEntity.getBackSheetThickness().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    PanelDTO fromPanelEntityToDTO(PanelEntity panelEntity);

    @Mapping(target = "maxLength", expression = "java(rebarEntity.getMaxLength() != null ? rebarEntity.getMaxLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "maxLengthUnit", expression = "java(rebarEntity.getMaxLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(rebarEntity.getMaxLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "weight", expression = "java(rebarEntity.getWeight() != null ? rebarEntity.getWeight().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "weightUnit", expression = "java(rebarEntity.getWeight().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.WeightUnits.valueOf(rebarEntity.getWeight().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    RebarDTO fromRebarEntityToDTO(RebarEntity rebarEntity);

    @Mapping(target = "galvanisedSheetThickness", expression = "java(setEntity.getGalvanisedSheetThickness() != null ? setEntity.getGalvanisedSheetThickness().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "galvanisedSheetThicknessUnit", expression = "java(setEntity.getGalvanisedSheetThickness().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(setEntity.getGalvanisedSheetThickness().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "maxLength", expression = "java(setEntity.getMaxLength() != null ? setEntity.getMaxLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "maxLengthUnit", expression = "java(setEntity.getMaxLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(setEntity.getMaxLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    SetDTO fromSetEntityToDTO(SetEntity setEntity);

    UnspecifiedDTO fromUnspecifiedEntityToDTO(UnspecifiedEntity unspecifiedEntity);

    ServiceDTO fromServiceEntityToDTO(ServiceEntity serviceEntity);

    @Mapping(target = "maxLength", expression = "java(transportEntity.getMaxLength() != null ? transportEntity.getMaxLength().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "maxLengthUnit", expression = "java(transportEntity.getMaxLength().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.LengthUnits.valueOf(transportEntity.getMaxLength().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    @Mapping(target = "weight", expression = "java(transportEntity.getWeight() != null ? transportEntity.getWeight().split(\"\\\\s+\")[0] : null)")
    @Mapping(target = "weightUnit", expression = "java(transportEntity.getWeight().split(\"\\\\s+\").length > 1 ? bg.mck.orderqueryservice.entity.enums.WeightUnits.valueOf(transportEntity.getWeight().split(\"\\\\s+\")[1].toUpperCase()) : null)")
    TransportDTO fromTransportEntityToDTO(TransportEntity transportEntity);

    void toUpdateFasterEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget FastenerEntity fastenerEntity);

    CreateUpdateOrderEvent toCreateUpdateOrderEvent(UpdateOrderDTO updateOrderDTO);

    void toUpdateGalvanisedEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);

    GalvanisedSheetEvent toUpdateGalvaniseSheet(UpdateOrderDTO updateOrderDTO);

    InsulationEvent toUpdateInsulation(UpdateOrderDTO updateOrderDTO);

    void toUpdateInsulationEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget InsulationEntity entity);

    MetalEvent toUpdateMetal(UpdateOrderDTO updateOrderDTO);

    void toUpdateMetalEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget MetalEntity entity);

    PanelEvent toUpdatePanel(UpdateOrderDTO updateOrderDTO);

    void toUpdatePanelEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget PanelEntity entity);

    ServiceEvent toUpdateService(UpdateOrderDTO updateOrderDTO);

    void toUpdateServiceEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget ServiceEntity entity);

    RebarEvent toUpdateRebar(UpdateOrderDTO updateOrderDTO);

    void toUpdateRebarEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget RebarEntity entity);

    SetEvent toUpdateSet(UpdateOrderDTO updateOrderDTO);

    void toUpdateSetEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget SetEntity entity);

    TransportEvent toUpdateTransport(UpdateOrderDTO updateOrderDTO);

    void toUpdateTransportEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget TransportEntity entity);

    UnspecifiedEvent toUpdateUnspecified(UpdateOrderDTO updateOrderDTO);

    void toUpdateUnspecifiedEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget UnspecifiedEntity entity);
}
