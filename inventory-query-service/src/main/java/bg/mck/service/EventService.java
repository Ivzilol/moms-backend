package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import static bg.mck.service.MaterialService.extractCategoryString;


@Service
public class EventService {

    private final EventRepository eventRepository;

    private final EventMaterialRepository eventMaterialRepository;
    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;
    private final ObjectMapper objectMapper;
    private final DeleteMaterialService deleteMaterialService;
    private final RedisService redisService;

    private final MaterialService materialService;

    public EventService(EventRepository eventRepository, ObjectMapper objectMapper, MaterialService materialService) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
        this.materialService = materialService;
        this.deleteMaterialService = deleteMaterialService;
        this.redisService = redisService;
    }

    public BaseMaterialEntity reconstructMaterialEntity(Long materialId, MaterialType materialType) {
        List<MaterialEvent<? extends BaseEvent>> events = eventMaterialRepository.findMaterialEventByMaterialTypeAndEventMaterialId(materialType, materialId);

        BaseMaterialEntity baseMaterialEntity = applyEvents(events, materialType);

        return baseMaterialEntity;

//        userRepository.save(userEntity);
//        redisService.cacheObject(userEntity);
//
//        return userEntity;
    }


    private BaseMaterialEntity applyEvents(List<MaterialEvent<? extends BaseEvent>> events, MaterialType materialType) {

        if (materialType.equals(MaterialType.FASTENERS)) {
            FastenerEntity fastenerEntity = new FastenerEntity();
            for (var event : events) {
                applyFastenerEvent(event, fastenerEntity);
            }

            return fastenerEntity;

        } else if (materialType.equals(MaterialType.GALVANIZED_SHEET)) {
            GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
            for (var event : events) {
                applyGalvanisedSheetEvent(event, galvanisedSheetEntity);
            }

            return null;
        } else if (materialType.equals(MaterialType.INSULATION)) {
            InsulationEntity insulationEntity = new InsulationEntity();
            for (var event : events) {
                applyInsulationEvent(event, insulationEntity);
            }
        } else if (materialType.equals(MaterialType.PANELS)) {
            PanelEntity panelEntity = new PanelEntity();
            for (var event : events) {
                applyPanelEvents(event, panelEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.REBAR)) {
            RebarEntity rebarEntity = new RebarEntity();
            for (var event : events) {
                applyRebarEvent(event, rebarEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.SET)) {
            SetEntity setEntity = new SetEntity();
            for (var event : events) {
                applySetEvent(event, setEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.UNSPECIFIED)) {
            UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
            for (var event : events) {
                applyUnspecifiedEvent(event, unspecifiedEntity);
            }
            return null;
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

        return null;
    }

    public void createMaterial(String data, String eventType) throws JsonProcessingException {
            String category = extractCategoryString(data);
        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
            category.equals(String.valueOf(MaterialType.FASTENERS))) {
            MaterialEvent<RegisterFastenerEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterFastenerEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterMaterial(saveEvent.getEvent());
        }
        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
            category.equals(String.valueOf(MaterialType.GALVANIZED_SHEET))) {
            MaterialEvent<RegisterGalvanizedEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterGalvanizedEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterGalvanized(saveEvent.getEvent());
        }
    public void processMaterialEvent(String data, String eventType, String materialType) throws JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {
            MaterialEvent<RegisterMaterialEvent> event = objectMapper.readValue(data, new TypeReference<>() {
            });
            Long materialId = event.getEvent().getMaterialId();
            saveEvent(event);
           //TODO:

        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            MaterialEvent<MaterialDeletedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
            });

            Long materialId = event.getEvent().getMaterialId();
            doesItemExist(materialId, materialType);
            saveEvent(event);
            deleteMaterialService.deleteMaterialByIdAndCategory(materialId, materialType);

        } else if (eventType.equals(EventType.ItemUpdated.name())) {
            MaterialEvent<MaterialUpdatedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
            });
            Long materialId = event.getEvent().getMaterialId();
            doesItemExist(materialId, materialType);
            saveEvent(event);
            reconstructMaterialEntity(materialId, MaterialType.valueOf(materialType));
        }

    }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
            category.equals(String.valueOf(MaterialType.INSULATION))) {
            MaterialEvent<RegisterInsulationEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterInsulationEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterInsulation(saveEvent.getEvent());
        }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
                category.equals(String.valueOf(MaterialType.METAL))) {
            MaterialEvent<RegisterMetalEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterMetalEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterMetal(saveEvent.getEvent());
        }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
                category.equals(String.valueOf(MaterialType.PANELS))) {
            MaterialEvent<RegisterPanelEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterPanelEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterPanel(saveEvent.getEvent());
        }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
                category.equals(String.valueOf(MaterialType.REBAR))) {
            MaterialEvent<RegisterRebarEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterRebarEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterRebar(saveEvent.getEvent());
        }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
                category.equals(String.valueOf(MaterialType.SET))) {
            MaterialEvent<RegisterSetEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterSetEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterSet(saveEvent.getEvent());
        }

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
                category.equals(String.valueOf(MaterialType.UNSPECIFIED))) {
            MaterialEvent<RegisterUnspecifiedEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterUnspecifiedEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterUnspecified(saveEvent.getEvent());
    private void applyFastenerEvent(MaterialEvent<? extends BaseEvent> materialEvent, BaseMaterialEntity materialEntity) {
        BaseEvent event = materialEvent.getEvent();

        if (event instanceof MaterialUpdatedEvent updateEvent) {

        } else if (event instanceof RegisterMaterialEvent registerEvent) {

        } else if (event instanceof MaterialDeletedEvent deletedEvent) {

        }
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
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }


    private <T extends BaseEvent> MaterialEvent<T> saveEvent(MaterialEvent<T> materialEvent) {
        return this.eventRepository.save(materialEvent);
    }

    private <T> T getItemByMaterialId(MongoRepository<T, Long> repository, Long materialId) {
        Optional<T> material = repository.findById(materialId);

        if (material.isPresent()) {
            return material.get();
        } else {
            throw new InventoryItemNotFoundException("Material with id " + materialId + " not found");
        }
    }
}
