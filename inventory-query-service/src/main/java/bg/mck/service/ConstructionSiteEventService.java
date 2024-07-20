package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.construction.ConstructionDeletedEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceDeletedEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import bg.mck.repository.constructionSite.EventConstructionSiteRepository;
import bg.mck.repository.transport.EventTransportRepository;
import bg.mck.repository.transport.TransportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ConstructionSiteEventService {


    private final ObjectMapper objectMapper;
    private final EventConstructionSiteRepository eventConstructionRepository;
    private final ConstructionSiteRepository constructionRepository;
    private final ConstructionRedisService redisService;
    private final ConstructionDeleteService constructionDeleteService;

    public ConstructionSiteEventService(ObjectMapper objectMapper, EventConstructionSiteRepository eventConstructionRepository, ConstructionSiteRepository constructionRepository, ConstructionRedisService redisService, ConstructionDeleteService constructionDeleteService) {
        this.objectMapper = objectMapper;
        this.eventConstructionRepository = eventConstructionRepository;
        this.constructionRepository = constructionRepository;
        this.redisService = redisService;
        this.constructionDeleteService = constructionDeleteService;
    }


    public void processConstructionEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {

        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            ConstructionEvent<ConstructionDeletedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
            });

            String constructionId = event.getEvent().getConstructionId();
            doesConstructionSiteExist(constructionId);
            saveEvent(event);
            evictCache(event.getEvent().getName());

            constructionDeleteService.deleteConstructionSiteById(constructionId);
            redisService.clearCacheForObject(constructionId);
        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new InvalidEventTypeException("Invalid event type: " + eventType);
        }

    }

    private void doesConstructionSiteExist(String serviceId) {
        boolean doesExist = constructionRepository.existsById(serviceId);
        if (!doesExist) {
            throw new InventoryItemNotFoundException("Construction site with id " + serviceId + " not found");
        }
    }

    private <T extends BaseConstructionEvent> ConstructionEvent<T> saveEvent(ConstructionEvent<T> constructionEvent) {
        return eventConstructionRepository.save(constructionEvent);
    }

    @CacheEvict(value = "constructions", key = "#constructionName.substring(0,2)")
    public void evictCache(String constructionName) {}
}
