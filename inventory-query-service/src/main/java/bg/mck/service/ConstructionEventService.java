package bg.mck.service;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.enums.EventType;
import bg.mck.events.construction.*;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import bg.mck.repository.constructionSite.EventConstructionSiteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructionEventService {


    private final ObjectMapper objectMapper;
    private final EventConstructionSiteRepository eventConstructionRepository;
    private final ConstructionSiteRepository constructionRepository;
    private final ConstructionDeleteService constructionDeleteService;
    private final ConstructionRegisterService constructionRegisterService;

    public ConstructionEventService(ObjectMapper objectMapper, EventConstructionSiteRepository eventConstructionRepository, ConstructionSiteRepository constructionRepository, ConstructionDeleteService constructionDeleteService, ConstructionRegisterService constructionRegisterService) {
        this.objectMapper = objectMapper;
        this.eventConstructionRepository = eventConstructionRepository;
        this.constructionRepository = constructionRepository;
        this.constructionDeleteService = constructionDeleteService;
        this.constructionRegisterService = constructionRegisterService;
    }

    public ConstructionSiteEntity reconstructConstructionEntity(String id) {
        doesConstructionSiteExist(id);
        List<BaseConstructionEvent> events = eventConstructionRepository
                .findConstructionEventByConstructionIdOrderByLocalDateTimeAsc(id);

        ConstructionSiteEntity constructionEntity = new ConstructionSiteEntity();
        constructionEntity.setId(id);

        for (var event : events) {
            applyEvent(event, constructionEntity);
        }

        constructionRepository.save(constructionEntity);

        return constructionEntity;
    }

    public void processConstructionEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {
            ConstructionRegisteredEvent event = objectMapper.readValue(data, ConstructionRegisteredEvent.class);

            saveEvent(event);
            evictCache(event.getName());

            constructionRegisterService.processingRegisterConstruction(event);
        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            ConstructionDeletedEvent event = objectMapper.readValue(data, ConstructionDeletedEvent.class);

            String constructionId = event.getConstructionId();
            doesConstructionSiteExist(constructionId);
            saveEvent(event);
            evictCache(event.getName());

            constructionDeleteService.deleteConstructionSiteById(constructionId);
        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new InvalidEventTypeException("Invalid event type: " + eventType);
        }

    }

    private void applyEvent(BaseConstructionEvent event, ConstructionSiteEntity constructionSiteEntity) {

        //TODO: implement the logic in the if-else condition
        if (event instanceof ConstructionUpdateEvent updateEvent) {

        } else if (event instanceof ConstructionRegisteredEvent registerEvent) {

        } else if (event instanceof ConstructionDeletedEvent deleteEvent) {

        }
    }

    private void doesConstructionSiteExist(String serviceId) {
        boolean doesExist = constructionRepository.existsById(serviceId);
        if (!doesExist) {
            throw new InventoryItemNotFoundException("Construction site with id " + serviceId + " not found");
        }
    }

    private BaseConstructionEvent saveEvent(BaseConstructionEvent constructionEvent) {
        return eventConstructionRepository.save(constructionEvent);
    }

    @CacheEvict(value = "constructions", key = "#constructionName.substring(0,2)")
    public void evictCache(String constructionName) {}
}
