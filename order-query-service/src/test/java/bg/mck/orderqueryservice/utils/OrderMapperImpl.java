package bg.mck.orderqueryservice.utils;
import bg.mck.orderqueryservice.dto.*;
import bg.mck.orderqueryservice.entity.*;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.*;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapperImpl {

    public OrderMapperImpl() {
    }

    public OrderDTO mapToOrderDTO(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderNumber(orderEntity.getOrderNumber());
        orderDTO.setOrderDescription(orderEntity.getOrderDescription());
        orderDTO.setOrderStatus(orderEntity.getOrderStatus());
        orderDTO.setMaterialType(orderEntity.getMaterialType());
        orderDTO.setSpecificationFileUrl(orderEntity.getSpecificationFileUrl());

        // Mapping material sets
        orderDTO.setFasteners(mapFasteners(orderEntity.getFasteners()));
        orderDTO.setGalvanisedSheets(mapGalvanisedSheets(orderEntity.getGalvanisedSheets()));
        orderDTO.setInsulation(mapInsulation(orderEntity.getInsulation()));
        orderDTO.setMetals(mapMetals(orderEntity.getMetals()));
        orderDTO.setPanels(mapPanels(orderEntity.getPanels()));
        orderDTO.setRebars(mapRebars(orderEntity.getRebars()));
        orderDTO.setSets(mapSets(orderEntity.getSets()));
        orderDTO.setUnspecified(mapUnspecified(orderEntity.getUnspecified()));
        orderDTO.setServices(mapServices(orderEntity.getServices()));
        orderDTO.setTransports(mapTransports(orderEntity.getTransports()));

        // Assuming ConstructionSiteDTO is mapped elsewhere or has its own mapper
        orderDTO.setConstructionSite(mapConstructionSite(orderEntity.getConstructionSite()));

        // Assuming orderDate and deliveryDate are set elsewhere or can be set here
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setDeliveryDate(orderEntity.getDeliveryDate());

        return orderDTO;
    }

    // Mapping methods for individual material sets
    private Set<FastenerDTO> mapFasteners(Set<FastenerEntity> fasteners) {
        if (fasteners == null) return null;
        return fasteners.stream()
                .map(this::toFastenerDTO)
                .collect(Collectors.toSet());
    }

    private Set<GalvanisedSheetDTO> mapGalvanisedSheets(Set<GalvanisedSheetEntity> galvanisedSheets) {
        if (galvanisedSheets == null) return null;
        return galvanisedSheets.stream()
                .map(this::toGalvanisedSheetDTO)
                .collect(Collectors.toSet());
    }

    private Set<InsulationDTO> mapInsulation(Set<InsulationEntity> insulation) {
        if (insulation == null) return null;
        return insulation.stream()
                .map(this::toInsulationDTO)
                .collect(Collectors.toSet());
    }

    private Set<MetalDTO> mapMetals(Set<MetalEntity> metals) {
        if (metals == null) return null;
        return metals.stream()
                .map(this::toMetalDTO)
                .collect(Collectors.toSet());
    }

    private Set<PanelDTO> mapPanels(Set<PanelEntity> panels) {
        if (panels == null) return null;
        return panels.stream()
                .map(this::toPanelDTO)
                .collect(Collectors.toSet());
    }

    private Set<RebarDTO> mapRebars(Set<RebarEntity> rebars) {
        if (rebars == null) return null;
        return rebars.stream()
                .map(this::toRebarDTO)
                .collect(Collectors.toSet());
    }

    private Set<SetDTO> mapSets(Set<SetEntity> sets) {
        if (sets == null) return null;
        return sets.stream()
                .map(this::toSetDTO)
                .collect(Collectors.toSet());
    }

    private Set<UnspecifiedDTO> mapUnspecified(Set<UnspecifiedEntity> unspecified) {
        if (unspecified == null) return null;
        return unspecified.stream()
                .map(this::toUnspecifiedDTO)
                .collect(Collectors.toSet());
    }

    private Set<ServiceDTO> mapServices(Set<ServiceEntity> services) {
        if (services == null) return null;
        return services.stream()
                .map(this::toServiceDTO)
                .collect(Collectors.toSet());
    }

    private Set<TransportDTO> mapTransports(Set<TransportEntity> transports) {
        if (transports == null) return null;
        return transports.stream()
                .map(this::toTransportDTO)
                .collect(Collectors.toSet());
    }

    // Conversion methods for each entity type to DTO type
    private FastenerDTO toFastenerDTO(FastenerEntity entity) {
        FastenerDTO dto = new FastenerDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private GalvanisedSheetDTO toGalvanisedSheetDTO(GalvanisedSheetEntity entity) {
        GalvanisedSheetDTO dto = new GalvanisedSheetDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setMaxLength(entity.getMaxLength());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private InsulationDTO toInsulationDTO(InsulationEntity entity) {
        InsulationDTO dto = new InsulationDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private MetalDTO toMetalDTO(MetalEntity entity) {
        MetalDTO dto = new MetalDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private PanelDTO toPanelDTO(PanelEntity entity) {
        PanelDTO dto = new PanelDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private RebarDTO toRebarDTO(RebarEntity entity) {
        RebarDTO dto = new RebarDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setMaxLength(entity.getMaxLength());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private SetDTO toSetDTO(SetEntity entity) {
        SetDTO dto = new SetDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setMaxLength(entity.getMaxLength());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private UnspecifiedDTO toUnspecifiedDTO(UnspecifiedEntity entity) {
        UnspecifiedDTO dto = new UnspecifiedDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private ServiceDTO toServiceDTO(ServiceEntity entity) {
        ServiceDTO dto = new ServiceDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    private TransportDTO toTransportDTO(TransportEntity entity) {
        TransportDTO dto = new TransportDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setSpecificationFileUrl(entity.getSpecificationFileUrl());
        dto.setMaxLength(entity.getMaxLength());
        dto.setWeight(entity.getWeight());
        dto.setAdminNote(entity.getAdminNote());
        dto.setMaterialStatus(entity.getMaterialStatus().name());
        return dto;
    }

    // Assuming ConstructionSiteDTO is handled by another mapper
    private ConstructionSiteDTO mapConstructionSite(ConstructionSiteEntity entity) {
        if (entity == null) {
            return null;
        }

        ConstructionSiteDTO dto = new ConstructionSiteDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setConstructionNumber(entity.getConstructionNumber());

        return dto;
    }

    public OrderEntity toOrderEntity(CreateOrderEvent<?> event) {
        OrderEntity orderEntity = new OrderEntity();

        // Set basic properties
        orderEntity.setId(String.valueOf(event.getOrderId()));
        orderEntity.setOrderNumber(event.getOrderNumber());
        orderEntity.setEmail(event.getEmail());
        orderEntity.setOrderDescription(event.getOrderDescription());
        orderEntity.setOrderStatus(event.getOrderStatus());

        // Map materials based on material type
        MaterialType materialType = event.getMaterialType();
        Set<?> materials = event.getMaterials();

        if (materialType == MaterialType.FASTENERS) {
            orderEntity.setFasteners(mapFasteners(materials, materialType));
        } else if (materialType == MaterialType.GALVANIZED_SHEET) {
            orderEntity.setGalvanisedSheets(mapGalvanisedSheets(materials, materialType));
        } else if (materialType == MaterialType.INSULATION) {
            orderEntity.setInsulation(mapInsulation(materials, materialType));
        } else if (materialType == MaterialType.METAL) {
            orderEntity.setMetals(mapMetals(materials, materialType));
        } else if (materialType == MaterialType.PANELS) {
            orderEntity.setPanels(mapPanels(materials, materialType));
        } else if (materialType == MaterialType.REBAR) {
            orderEntity.setRebars(mapRebars(materials, materialType));
        } else if (materialType == MaterialType.SET) {
            orderEntity.setSets(mapSets(materials, materialType));
        } else if (materialType == MaterialType.UNSPECIFIED) {
            orderEntity.setUnspecified(mapUnspecified(materials, materialType));
        } else if (materialType == MaterialType.SERVICE) {
            orderEntity.setServices(mapServices(materials, materialType));
        } else if (materialType == MaterialType.TRANSPORT) {
            orderEntity.setTransports(mapTransports(materials, materialType));
        }

        return orderEntity;
    }

    // Mapping methods for each material type
    private Set<FastenerEntity> mapFasteners(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.FASTENERS && materials != null) {
            return materials.stream()
                    .filter(FasterEvent.class::isInstance)
                    .map(FasterEvent.class::cast)
                    .map(this::toFasterEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<GalvanisedSheetEntity> mapGalvanisedSheets(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.GALVANIZED_SHEET && materials != null) {
            return materials.stream()
                    .filter(GalvanisedSheetEvent.class::isInstance)
                    .map(GalvanisedSheetEvent.class::cast)
                    .map(this::toGalvanisedSheetEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<InsulationEntity> mapInsulation(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.INSULATION && materials != null) {
            return materials.stream()
                    .filter(InsulationEvent.class::isInstance)
                    .map(InsulationEvent.class::cast)
                    .map(this::toInsulationEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<MetalEntity> mapMetals(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.METAL && materials != null) {
            return materials.stream()
                    .filter(MetalEvent.class::isInstance)
                    .map(MetalEvent.class::cast)
                    .map(this::toMetalEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<PanelEntity> mapPanels(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.PANELS && materials != null) {
            return materials.stream()
                    .filter(PanelEvent.class::isInstance)
                    .map(PanelEvent.class::cast)
                    .map(this::toPanelEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<RebarEntity> mapRebars(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.REBAR && materials != null) {
            return materials.stream()
                    .filter(RebarEvent.class::isInstance)
                    .map(RebarEvent.class::cast)
                    .map(this::toRebarEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<SetEntity> mapSets(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.SET && materials != null) {
            return materials.stream()
                    .filter(SetEvent.class::isInstance)
                    .map(SetEvent.class::cast)
                    .map(this::toSetEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<UnspecifiedEntity> mapUnspecified(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.UNSPECIFIED && materials != null) {
            return materials.stream()
                    .filter(UnspecifiedEvent.class::isInstance)
                    .map(UnspecifiedEvent.class::cast)
                    .map(this::toUnspecifiedEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<ServiceEntity> mapServices(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.SERVICE && materials != null) {
            return materials.stream()
                    .filter(ServiceEvent.class::isInstance)
                    .map(ServiceEvent.class::cast)
                    .map(this::toServiceEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<TransportEntity> mapTransports(Set<?> materials, MaterialType materialType) {
        if (materialType == MaterialType.TRANSPORT && materials != null) {
            return materials.stream()
                    .filter(TransportEvent.class::isInstance)
                    .map(TransportEvent.class::cast)
                    .map(this::toTransportEntity)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    // Conversion methods for each event type to entity type
    private FastenerEntity toFasterEntity(FasterEvent event) {
        FastenerEntity entity = new FastenerEntity();
        // Map fields from FasterEvent to FastenerEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private GalvanisedSheetEntity toGalvanisedSheetEntity(GalvanisedSheetEvent event) {
        GalvanisedSheetEntity entity = new GalvanisedSheetEntity();
        // Map fields from GalvanisedSheetEvent to GalvanisedSheetEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setMaxLength(event.getMaxLength());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private InsulationEntity toInsulationEntity(InsulationEvent event) {
        InsulationEntity entity = new InsulationEntity();
        // Map fields from InsulationEvent to InsulationEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private MetalEntity toMetalEntity(MetalEvent event) {
        MetalEntity entity = new MetalEntity();
        // Map fields from MetalEvent to MetalEntity
        entity.setId(event.getId().toString());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private PanelEntity toPanelEntity(PanelEvent event) {
        PanelEntity entity = new PanelEntity();
        // Map fields from PanelEvent to PanelEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private RebarEntity toRebarEntity(RebarEvent event) {
        RebarEntity entity = new RebarEntity();
        // Map fields from RebarEvent to RebarEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setMaxLength(event.getMaxLength());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private SetEntity toSetEntity(SetEvent event) {
        SetEntity entity = new SetEntity();
        // Map fields from SetEvent to SetEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setMaxLength(event.getMaxLength());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private UnspecifiedEntity toUnspecifiedEntity(UnspecifiedEvent event) {
        UnspecifiedEntity entity = new UnspecifiedEntity();
        // Map fields from UnspecifiedEvent to UnspecifiedEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private ServiceEntity toServiceEntity(ServiceEvent event) {
        ServiceEntity entity = new ServiceEntity();
        // Map fields from ServiceEvent to ServiceEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }

    private TransportEntity toTransportEntity(TransportEvent event) {
        TransportEntity entity = new TransportEntity();
        // Map fields from TransportEvent to TransportEntity
        entity.setId(event.getId().toString());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        entity.setMaxLength(event.getMaxLength());
        entity.setWeight(event.getWeight());
        entity.setAdminNote(event.getAdminNote());
        entity.setMaterialStatus(event.getMaterialStatus());
        return entity;
    }
}

