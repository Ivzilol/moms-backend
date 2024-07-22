package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.dto.UpdateOrderDTO;
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

    void toUpdateFasterEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget FastenerEntity fastenerEntity);

    CreateUpdateOrderEvent toCreateUpdateOrderEvent(UpdateOrderDTO updateOrderDTO);

    void toUpdateGalvanisedEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);

    GalvanisedSheetEvent toUpdateGalvaniseSheet(UpdateOrderDTO updateOrderDTO);

    InsulationEvent toUpdateInsulation(UpdateOrderDTO updateOrderDTO);

    void toUpdateInsulationEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget InsulationEntity entity);

    MetalEvent toUpdateMetal(UpdateOrderDTO updateOrderDTO);

    void toUpdateMetalEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget MetalEntity entity);
}
