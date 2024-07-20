package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceDeletedEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.service.EventServiceRepository;
import bg.mck.repository.service.ServiceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ServiceEventService {


    private final ObjectMapper objectMapper;
    private final EventServiceRepository eventServiceRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceRedisService redisService;
    private final ServiceDeleteService serviceDeleteService;


    public ServiceEventService(ObjectMapper objectMapper, EventServiceRepository eventServiceRepository, ServiceRepository serviceRepository, ServiceRedisService redisService, ServiceDeleteService serviceDeleteService) {
        this.objectMapper = objectMapper;
        this.eventServiceRepository = eventServiceRepository;
        this.serviceRepository = serviceRepository;
        this.redisService = redisService;
        this.serviceDeleteService = serviceDeleteService;
    }


    public void processServiceEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {

        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            ServiceEvent<ServiceDeletedEvent> event = objectMapper.readValue(data, new TypeReference<>() {
            });

            String serviceId = event.getEvent().getServiceId();
            doesServiceExist(serviceId);
            saveEvent(event);
            evictCache(event.getEvent().getName());

            serviceDeleteService.deleteServiceById(serviceId);
            redisService.clearCacheForObject(serviceId);
        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new InvalidEventTypeException("Invalid event type: " + eventType);
        }

    }


    private void doesServiceExist(String serviceId) {
        boolean doesExist = serviceRepository.existsById(serviceId);
        if (!doesExist) {
            throw new InventoryItemNotFoundException("Service with id " + serviceId + " not found");
        }
    }

    private <T extends BaseServiceEvent> ServiceEvent<T> saveEvent(ServiceEvent<T> serviceEvent) {
        return eventServiceRepository.save(serviceEvent);
    }

    @CacheEvict(value = "services", key = "#serviceName.substring(0,2)")
    public void evictCache(String serviceName) {}


}