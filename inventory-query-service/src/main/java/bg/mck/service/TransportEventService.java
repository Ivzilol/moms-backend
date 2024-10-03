package bg.mck.service;

import bg.mck.entity.transportEntity.TransportEntity;
import bg.mck.enums.EventType;
import bg.mck.events.transport.*;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.transport.EventTransportRepository;
import bg.mck.repository.transport.TransportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportEventService {


    private final ObjectMapper objectMapper;
    private final EventTransportRepository eventTransportRepository;
    private final TransportRepository transportRepository;
    private final TransportDeleteService transportDeleteService;
    private final TransportRegisterService transportRegisterService;

    public TransportEventService(ObjectMapper objectMapper, EventTransportRepository eventTransportRepository, TransportRepository transportRepository, TransportDeleteService transportDeleteService, TransportRegisterService transportRegisterService) {
        this.objectMapper = objectMapper;
        this.eventTransportRepository = eventTransportRepository;
        this.transportRepository = transportRepository;
        this.transportDeleteService = transportDeleteService;
        this.transportRegisterService = transportRegisterService;
    }

    public TransportEntity reconstructTransportEntity(String id) {
        doesTransportExist(id);
        List<BaseTransportEvent> events = eventTransportRepository
                .findTransportEventsByTransportIdOrderByLocalDateTimeAsc(id);

        TransportEntity transportEntity = new TransportEntity();
        transportEntity.setId(id);

        for (var event : events) {
            applyEvent(event, transportEntity);
        }

        transportRepository.save(transportEntity);

        return transportEntity;
    }

    public void processTransportEvent(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(EventType.ItemRegistered.name())) {
            TransportRegisteredEvent event = objectMapper.readValue(data, TransportRegisteredEvent.class);

            saveEvent(event);
            evictCache(event.getName());

            transportRegisterService.processingRegisterTransport(event);
        } else if (eventType.equals(EventType.ItemDeleted.name())) {
            TransportDeletedEvent event = objectMapper.readValue(data, TransportDeletedEvent.class);

            String transportId = event.getTransportId();
            doesTransportExist(transportId);
            saveEvent(event);
            evictCache(event.getName());

            transportDeleteService.deleteTransportById(transportId);
        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new InvalidEventTypeException("Invalid event type: " + eventType);
        }

    }

    private void applyEvent(BaseTransportEvent event, TransportEntity transportEntity) {


        //TODO: implement the logic in the if-else condition
        if (event instanceof TransportUpdateEvent updateEvent) {

        } else if (event instanceof TransportRegisteredEvent registerEvent) {

        } else if (event instanceof TransportDeletedEvent deleteEvent) {

        }
    }

    private void doesTransportExist(String transportId) {
        boolean doesExist = transportRepository.existsById(transportId);
        if (!doesExist) {
            throw new InventoryItemNotFoundException("Transport with id " + transportId + " not found");
        }
    }

    private BaseTransportEvent saveEvent(BaseTransportEvent transportEvent) {
        return eventTransportRepository.save(transportEvent);
    }

    @CacheEvict(value = "transports", key = "#transportName.substring(0,2)")
    public void evictCache(String transportName) {}
}
