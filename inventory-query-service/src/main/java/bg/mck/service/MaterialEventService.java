package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.*;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.material.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public MaterialEventService(EventMaterialRepository eventMaterialRepository, FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository,
                                PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository,
                                ObjectMapper objectMapper, MaterialDeleteService materialDeleteService, MaterialRedisService materialRedisService, MaterialRegisterService materialRegisterService) {
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
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

    }



    @SuppressWarnings("unchecked")
    public <T extends BaseMaterialEntity> T reconstructMaterialEntity(Long materialId, String materialType, Class<T> clazz) {
        List<MaterialEvent<? extends BaseEvent>> events = eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(materialId, materialType);
        return (T) applyEvents(events, materialType);
    }

    private BaseMaterialEntity applyEvents(List<MaterialEvent<? extends BaseEvent>> events, String materialType) {
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
        }
        else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

    }

    private void applyMetalEvent(MaterialEvent<? extends BaseEvent> event, MetalEntity metalEntity) {


    }

    private void applyUnspecifiedEvent(MaterialEvent<? extends BaseEvent> event, UnspecifiedEntity unspecifiedEntity) {

    }

    private void applySetEvent(MaterialEvent<? extends BaseEvent> event, SetEntity setEntity) {

    }

    private void applyRebarEvent(MaterialEvent<? extends BaseEvent> event, RebarEntity rebarEntity) {

    }

    private void applyPanelEvents(MaterialEvent<? extends BaseEvent> event, PanelEntity panelEntity) {

    }

    private void applyInsulationEvent(MaterialEvent<? extends BaseEvent> event, InsulationEntity insulationEntity) {

    }

    private void applyGalvanisedSheetEvent(MaterialEvent<? extends BaseEvent> event, GalvanisedSheetEntity galvanisedSheetEntity) {

    }


    private void applyFastenerEvent(MaterialEvent<? extends BaseEvent> materialEvent, FastenerEntity entity) {
        BaseEvent event = materialEvent.getEvent();

        if (event instanceof UpdateFastenerEvent) {

        } else if (event instanceof RegisterFastenerEvent registerEvent) {

        } else if (event instanceof MaterialDeletedEvent deletedEvent) {

        }
    }


    private <T extends BaseEvent> MaterialEvent<T> saveEvent(MaterialEvent<T> userEvent) {
        return eventMaterialRepository.save(userEvent);
    }

    private void doesItemExist(Long materialId, String materialType) {
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


    private <T> T getItemByMaterialId(MongoRepository<T, String> repository, Long materialId) {
        Optional<T> material = repository.findById(String.valueOf(materialId));

        if (material.isPresent()) {
            return material.get();
        } else {
            throw new InventoryItemNotFoundException("Material with id " + materialId + " not found");
        }
    }

    private void registerEvent(String data, String category) throws JsonProcessingException {
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            MaterialEvent<RegisterFastenerEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterFastenerEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterMaterial(saveEvent.getEvent());
        }
        if (category.equals(String.valueOf(MaterialType.GALVANIZED_SHEET))) {
            MaterialEvent<RegisterGalvanizedEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterGalvanizedEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterGalvanized(saveEvent.getEvent());
        }


        if (category.equals(String.valueOf(MaterialType.INSULATION))) {
            MaterialEvent<RegisterInsulationEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterInsulationEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterInsulation(saveEvent.getEvent());
        }

        if (category.equals(String.valueOf(MaterialType.METAL))) {
            MaterialEvent<RegisterMetalEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterMetalEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterMetal(saveEvent.getEvent());
        }

        if (category.equals(String.valueOf(MaterialType.PANELS))) {
            MaterialEvent<RegisterPanelEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterPanelEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterPanel(saveEvent.getEvent());
        }

        if (category.equals(String.valueOf(MaterialType.REBAR))) {
            MaterialEvent<RegisterRebarEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterRebarEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterRebar(saveEvent.getEvent());
        }

        if (category.equals(String.valueOf(MaterialType.SET))) {
            MaterialEvent<RegisterSetEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterSetEvent> saveEvent = saveEvent(materialEvent);
            this.materialRegisterService.processingRegisterSet(saveEvent.getEvent());
        }

        if (category.equals(String.valueOf(MaterialType.UNSPECIFIED))) {
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
        //TODO:
        if (materialType.equals(String.valueOf(MaterialType.FASTENERS))) {
            MaterialEvent<UpdateFastenerEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            Long materialId = materialEvent.getEvent().getMaterialId();
            doesItemExist(materialId, materialType);
            saveEvent(materialEvent);
            evictCache(materialType, materialEvent.getEvent().getName());

            reconstructMaterialEntity(materialId, materialType, FastenerEntity.class);
        }
    }

    private void deleteEvent(String data, String materialType) throws JsonProcessingException {
        MaterialEvent<MaterialDeletedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
        });

        Long materialId = event.getEvent().getMaterialId();
        doesItemExist(materialId, materialType);
        saveEvent(event);
        evictCache(materialType, event.getEvent().getName());

        materialDeleteService.deleteMaterialByIdAndCategory(String.valueOf(materialId), materialType);
        materialRedisService.clearCacheForObject(String.valueOf(materialId), materialType);
    }

    @CacheEvict(value = "materials", key = "#category + '_' + #materialName.substring(0,2)")
    public void evictCache(String category, String materialName) {}


}
