package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.*;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryQueryUpdateMapper;
import bg.mck.repository.material.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MaterialEventService {

    private final EventMaterialRepository eventMaterialRepository;
    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;
    private final MetalRepository metalRepository;
    private final ObjectMapper objectMapper;
    private final MaterialDeleteService materialDeleteService;
    private final MaterialRedisService materialRedisService;
    private final MaterialRegisterService materialRegisterService;
    private final InventoryQueryUpdateMapper inventoryQueryUpdateMapper;
    private final CacheManager cacheManager;


    public MaterialEventService(EventMaterialRepository eventMaterialRepository, FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository,
                                PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository,
                                ObjectMapper objectMapper, MaterialDeleteService materialDeleteService, MaterialRedisService materialRedisService, MaterialRegisterService materialRegisterService, InventoryQueryUpdateMapper inventoryQueryUpdateMapper, CacheManager cacheManager) {
        this.eventMaterialRepository = eventMaterialRepository;
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.metalRepository = metalRepository;
        this.objectMapper = objectMapper;
        this.materialDeleteService = materialDeleteService;
        this.materialRedisService = materialRedisService;
        this.materialRegisterService = materialRegisterService;
        this.inventoryQueryUpdateMapper = inventoryQueryUpdateMapper;
        this.cacheManager = cacheManager;
    }

    public void processMaterialEvent(String data, String eventType, String materialType) throws
            JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {
            registerEvent(data, materialType);

        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            deleteEvent(data, materialType);

        } else if (eventType.equals(EventType.ItemUpdated.name())) {
            updateEvent(data, materialType);

        } else {
            throw new InvalidEventTypeException("Invalid event type: " + eventType);
        }

    }


    @SuppressWarnings("unchecked")
    public <T extends BaseMaterialEntity> T reconstructMaterialEntity(String materialId, String materialType, Class<T> clazz) {
        doesItemExist(materialId, materialType);
        List<MaterialEvent<? extends BaseMaterialEvent>> events = eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), materialType);
        return (T) applyEvents(events, materialType);
    }

    private BaseMaterialEntity applyEvents(List<MaterialEvent<? extends BaseMaterialEvent>> events, String materialType) {
        if (materialType.equals(MaterialType.FASTENERS.name())) {
            FastenerEntity fastenerEntity = new FastenerEntity();
            for (var event : events) {
                applyFastenerEvent(event, fastenerEntity);
            }

            fastenerRepository.save(fastenerEntity);
            materialRedisService.cacheObject(fastenerEntity, materialType);
            return fastenerEntity;
        } else if (materialType.equals(MaterialType.GALVANIZED_SHEET.name())) {
            GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
            for (var event : events) {
                applyGalvanisedSheetEvent(event, galvanisedSheetEntity);
            }

            galvanisedSheetRepository.save(galvanisedSheetEntity);
            materialRedisService.cacheObject(galvanisedSheetEntity, materialType);
            return galvanisedSheetEntity;
        } else if (materialType.equals(MaterialType.INSULATION.name())) {
            InsulationEntity insulationEntity = new InsulationEntity();
            for (var event : events) {
                applyInsulationEvent(event, insulationEntity);
            }
            insulationRepository.save(insulationEntity);
            materialRedisService.cacheObject(insulationEntity, materialType);
            return insulationEntity;
        } else if (materialType.equals(MaterialType.PANELS.name())) {
            PanelEntity panelEntity = new PanelEntity();
            for (var event : events) {
                applyPanelEvents(event, panelEntity);
            }
            panelRepository.save(panelEntity);
            materialRedisService.cacheObject(panelEntity, materialType);
            return panelEntity;
        } else if (materialType.equals(MaterialType.REBAR.name())) {
            RebarEntity rebarEntity = new RebarEntity();
            for (var event : events) {
                applyRebarEvent(event, rebarEntity);
            }
            rebarRepository.save(rebarEntity);
            materialRedisService.cacheObject(rebarEntity, materialType);
            return rebarEntity;
        } else if (materialType.equals(MaterialType.SET.name())) {
            SetEntity setEntity = new SetEntity();
            for (var event : events) {
                applySetEvent(event, setEntity);
            }
            setRepository.save(setEntity);
            materialRedisService.cacheObject(setEntity, materialType);
            return setEntity;
        } else if (materialType.equals(MaterialType.UNSPECIFIED.name())) {
            UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
            for (var event : events) {
                applyUnspecifiedEvent(event, unspecifiedEntity);
            }
            unspecifiedRepository.save(unspecifiedEntity);
            materialRedisService.cacheObject(unspecifiedEntity, materialType);
            return unspecifiedEntity;
        } else if (materialType.equals(MaterialType.METAL.name())) {
            MetalEntity metalEntity = new MetalEntity();
            for (var event : events) {
                applyMetalEvent(event, metalEntity);
            }
            metalRepository.save(metalEntity);
            materialRedisService.cacheObject(metalEntity, materialType);
            return metalEntity;
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

    }

    private void applyMetalEvent(MaterialEvent<? extends BaseMaterialEvent> event, MetalEntity metalEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateMetalEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateMetalEventToMetalEntity(updateEvent, metalEntity);
        } else if (baseEvent instanceof RegisterMetalEvent registerEvent) {
            inventoryQueryUpdateMapper.mapMetalRegisterEventToEntity(registerEvent, metalEntity);
            metalEntity.setId(registerEvent.getMaterialId().toString());
        }

    }

    private void applyUnspecifiedEvent(MaterialEvent<? extends BaseMaterialEvent> event, UnspecifiedEntity unspecifiedEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateUnspecifiedEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateUnspecifiedEventToUnspecifiedEntity(updateEvent, unspecifiedEntity);
        } else if (baseEvent instanceof RegisterUnspecifiedEvent registerEvent) {
            inventoryQueryUpdateMapper.mapUnspecifiedRegisterEventToEntity(registerEvent, unspecifiedEntity);
            unspecifiedEntity.setId(registerEvent.getMaterialId().toString());
        }
    }

    private void applySetEvent(MaterialEvent<? extends BaseMaterialEvent> event, SetEntity setEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateSetEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateSetEventToSetEntity(updateEvent, setEntity);
        } else if (baseEvent instanceof RegisterSetEvent registerEvent) {
            inventoryQueryUpdateMapper.mapSetRegisterEventToEntity(registerEvent, setEntity);
            setEntity.setId(registerEvent.getMaterialId().toString());
        }
    }

    private void applyRebarEvent(MaterialEvent<? extends BaseMaterialEvent> event, RebarEntity rebarEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateRebarEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateRebarEventToRebarEntity(updateEvent, rebarEntity);
        } else if (baseEvent instanceof RegisterRebarEvent registerEvent) {
            inventoryQueryUpdateMapper.mapRebarRegisterEventToEntity(registerEvent, rebarEntity);
            rebarEntity.setId(registerEvent.getMaterialId().toString());
        }
    }

    private void applyPanelEvents(MaterialEvent<? extends BaseMaterialEvent> event, PanelEntity panelEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdatePanelEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdatePanelEventToPanelEntity(updateEvent, panelEntity);
        } else if (baseEvent instanceof RegisterPanelEvent registerEvent) {
            inventoryQueryUpdateMapper.mapPanelRegisterEventToEntity(registerEvent, panelEntity);
            panelEntity.setId(registerEvent.getMaterialId().toString());
        }
    }

    private void applyInsulationEvent(MaterialEvent<? extends BaseMaterialEvent> event, InsulationEntity insulationEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateInsulationEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateInsulationEventToInsulationEntity(updateEvent, insulationEntity);
        } else if (baseEvent instanceof RegisterInsulationEvent registerEvent) {
            inventoryQueryUpdateMapper.mapInsulationRegisterEventToEntity(registerEvent, insulationEntity);
            insulationEntity.setId(registerEvent.getMaterialId().toString());
        }
    }

    private void applyGalvanisedSheetEvent(MaterialEvent<? extends BaseMaterialEvent> event, GalvanisedSheetEntity galvanisedSheetEntity) {
        BaseMaterialEvent baseEvent = event.getEvent();
        if (baseEvent instanceof UpdateGalvanizedSheetEvent updateEvent) {
            inventoryQueryUpdateMapper.mapUpdateGalvanizedSheetEventToGalvanizedSheetEntity(updateEvent, galvanisedSheetEntity);
        } else if (baseEvent instanceof RegisterGalvanizedEvent registerEvent) {
            inventoryQueryUpdateMapper.mapGalvanizedSheetRegisterEventToEntity(registerEvent, galvanisedSheetEntity);
            galvanisedSheetEntity.setId(registerEvent.getMaterialId().toString());
        }
    }


    private void applyFastenerEvent(MaterialEvent<? extends BaseMaterialEvent> materialEvent, FastenerEntity entity) {
        BaseMaterialEvent event = materialEvent.getEvent();
        if (event instanceof UpdateFastenerEvent updateEvent) {
            inventoryQueryUpdateMapper.mapFastenerUpdateEventToFastenerEntity(updateEvent, entity);
        } else if (event instanceof RegisterFastenerEvent registerEvent) {
            inventoryQueryUpdateMapper.mapFastenerRegisterEventToFastenerEntity(registerEvent, entity);
            entity.setId(registerEvent.getMaterialId().toString());
        }
    }


    private <T extends BaseMaterialEvent> MaterialEvent<T> saveEvent(MaterialEvent<T> materialEvent) {
        return eventMaterialRepository.save(materialEvent);
    }

    private void doesItemExist(String materialId, String materialType) {
        if (materialType.equals(MaterialType.FASTENERS.name())) {
            getItemByMaterialId(fastenerRepository, materialId);
        } else if (materialType.equals(MaterialType.GALVANIZED_SHEET.name())) {
            getItemByMaterialId(galvanisedSheetRepository, materialId);
        } else if (materialType.equals(MaterialType.INSULATION.name())) {
            getItemByMaterialId(insulationRepository, materialId);
        } else if (materialType.equals(MaterialType.PANELS.name())) {
            getItemByMaterialId(panelRepository, materialId);
        } else if (materialType.equals(MaterialType.REBAR.name())) {
            getItemByMaterialId(rebarRepository, materialId);
        } else if (materialType.equals(MaterialType.SET.name())) {
            getItemByMaterialId(setRepository, materialId);
        } else if (materialType.equals(MaterialType.UNSPECIFIED.name())) {
            getItemByMaterialId(unspecifiedRepository, materialId);
        } else if (materialType.equals(MaterialType.METAL.name())) {
            getItemByMaterialId(metalRepository, materialId);
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }
    }


    private <T> T getItemByMaterialId(MongoRepository<T, String> repository, String materialId) {
        Optional<T> material = repository.findById(materialId);

        if (material.isPresent()) {
            return material.get();
        } else {
            throw new InventoryItemNotFoundException("Material with id " + materialId + " not found");
        }
    }

    private void registerEvent(String data, String category) throws JsonProcessingException {
        if (category.equals(MaterialType.FASTENERS.name())) {
            MaterialEvent<RegisterFastenerEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterFastenerEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterMaterial(saveEvent.getEvent());
        } else if (category.equals(MaterialType.GALVANIZED_SHEET.name())) {
            MaterialEvent<RegisterGalvanizedEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterGalvanizedEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterGalvanized(saveEvent.getEvent());
        } else if (category.equals(MaterialType.INSULATION.name())) {
            MaterialEvent<RegisterInsulationEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterInsulationEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterInsulation(saveEvent.getEvent());
        } else if (category.equals(MaterialType.METAL.name())) {
            MaterialEvent<RegisterMetalEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterMetalEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterMetal(saveEvent.getEvent());
        } else if (category.equals(MaterialType.PANELS.name())) {
            MaterialEvent<RegisterPanelEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterPanelEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterPanel(saveEvent.getEvent());
        } else if (category.equals(MaterialType.REBAR.name())) {
            MaterialEvent<RegisterRebarEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterRebarEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterRebar(saveEvent.getEvent());
        } else if (category.equals(MaterialType.SET.name())) {
            MaterialEvent<RegisterSetEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterSetEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterSet(saveEvent.getEvent());
        } else if (category.equals(MaterialType.UNSPECIFIED.name())) {
            MaterialEvent<RegisterUnspecifiedEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterUnspecifiedEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterUnspecified(saveEvent.getEvent());
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + category);
        }
    }


    private void updateEvent(String data, String materialType) throws JsonProcessingException {
        if (materialType.equals(MaterialType.FASTENERS.name())) {
            updateFastenerEntity(data, materialType);
        } else if (materialType.equals(MaterialType.GALVANIZED_SHEET.name())) {
            updateGalvanizedSheetEntity(data, materialType);
        } else if (materialType.equals(MaterialType.SET.name())) {
            updateSetEntity(data, materialType);
        } else if (materialType.equals(MaterialType.METAL.name())) {
            updateMetalEntity(data, materialType);
        } else if (materialType.equals(MaterialType.INSULATION.name())) {
            updateInsulationEntity(data, materialType);
        } else if (materialType.equals(MaterialType.PANELS.name())) {
            updatePanelEntity(data, materialType);
        } else if (materialType.equals(MaterialType.REBAR.name())) {
            updateRebarEntity(data, materialType);
        } else if (materialType.equals(MaterialType.UNSPECIFIED.name())) {
            updateUnspecifiedEntity(data, materialType);
        }

    }

    private void updateUnspecifiedEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateUnspecifiedEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, UnspecifiedEntity.class);
    }

    private void updateRebarEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateRebarEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, RebarEntity.class);
    }

    private void updatePanelEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdatePanelEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, PanelEntity.class);
    }

    private void updateInsulationEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateInsulationEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, InsulationEntity.class);
    }

    private void updateMetalEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateMetalEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, MetalEntity.class);
    }

    private void updateSetEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateSetEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, SetEntity.class);
    }

    private void updateGalvanizedSheetEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateGalvanizedSheetEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });

        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());
        reconstructMaterialEntity(materialId, materialType, GalvanisedSheetEntity.class);
    }


    private void updateFastenerEntity(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<UpdateFastenerEvent> materialEvent =
                objectMapper.readValue(data, new TypeReference<>() {
                });
        String materialId = materialEvent.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(materialEvent);
        evictCache(materialType, materialEvent.getEvent().getName());

        reconstructMaterialEntity(materialId, materialType, FastenerEntity.class);
    }

    private void deleteEvent(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<MaterialDeletedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
        });

        String materialId = event.getEvent().getMaterialId().toString();
        doesItemExist(materialId, materialType);
        saveEvent(event);
        evictCache(materialType, event.getEvent().getName());

        materialDeleteService.deleteMaterialByIdAndCategory(materialId, materialType);
        materialRedisService.clearCacheForObject(materialId, materialType);
    }

    public void evictCache(String category, String materialName) {
        String cacheKey = category + "_" + materialName.substring(0, 2);

        Cache cache = cacheManager.getCache("materials");

        if (cache != null) {
            cache.evict(cacheKey);
        }
    }


}
