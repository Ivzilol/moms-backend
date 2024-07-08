package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.*;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

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

    public EventService(EventMaterialRepository eventMaterialRepository, FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, ObjectMapper objectMapper, DeleteMaterialService deleteMaterialService, RedisService redisService) {
        this.eventMaterialRepository = eventMaterialRepository;
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.objectMapper = objectMapper;
        this.deleteMaterialService = deleteMaterialService;
        this.redisService = redisService;
    }

    public <T extends BaseEvent> void processMaterialEvent(MaterialEvent<T> data, String eventType, String materialType) {
        if (eventType.equals(EventType.ItemRegistered.name())) {
            MaterialEvent<RegisterMaterialEvent> event = (MaterialEvent<RegisterMaterialEvent>) data;
            Long materialId = event.getEvent().getMaterialId();
            saveEvent(event);

            //TODO:
        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            MaterialEvent<MaterialDeletedEvent> event = (MaterialEvent<MaterialDeletedEvent>) data;

            Long materialId = event.getEvent().getMaterialId();
            doesItemExist(materialId, materialType);
            saveEvent(event);
            deleteMaterialService.deleteMaterialByIdAndCategory(materialId, materialType);

        } else if (eventType.equals(EventType.ItemUpdated.name())) {
            // TODO:

        }

    }

    public BaseMaterialEntity reconstructMaterialEntity(Long materialId, MaterialType materialType) {
        List<MaterialEvent<? extends BaseEvent>> events = eventMaterialRepository.
                findMaterialEventByMaterialTypeAndEventMaterialId(materialType, materialId);

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
            for (var event :events) {
                applyGalvanisedSheetEvent(event, galvanisedSheetEntity);
            }

            return null;
        } else if (materialType.equals(MaterialType.INSULATION)) {
            InsulationEntity insulationEntity = new InsulationEntity();
            for (var event :events) {
                applyInsulationEvent(event, insulationEntity);
            }
        } else if (materialType.equals(MaterialType.PANELS)) {
            PanelEntity panelEntity = new PanelEntity();
            for (var event :events) {
                applyPanelEvents(event, panelEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.REBAR)) {
            RebarEntity rebarEntity = new RebarEntity();
            for (var event :events) {
                applyRebarEvent(event, rebarEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.SET)) {
            SetEntity setEntity = new SetEntity();
            for (var event :events) {
                applySetEvent(event, setEntity);
            }
            return null;
        } else if (materialType.equals(MaterialType.UNSPECIFIED)) {
            UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
            for (var event :events) {
                applyUnspecifiedEvent(event, unspecifiedEntity);
            }
            return null;
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

        return null;
    }

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
