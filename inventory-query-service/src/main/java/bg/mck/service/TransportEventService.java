package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.repository.service.EventServiceRepository;
import bg.mck.repository.service.ServiceRepository;
import bg.mck.repository.transport.EventTransportRepository;
import bg.mck.repository.transport.TransportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class TransportEventService {


    private final ObjectMapper objectMapper;
    private final EventTransportRepository eventTransportRepository;
    private final TransportRepository transportRepository;

    public TransportEventService(ObjectMapper objectMapper, EventTransportRepository eventTransportRepository, TransportRepository transportRepository) {
        this.objectMapper = objectMapper;
        this.eventTransportRepository = eventTransportRepository;
        this.transportRepository = transportRepository;
    }


    public void processTransportEvent(String data, String eventType) {
        if (eventType.equals(EventType.ItemRegistered.name())) {

        } else if (eventType.equals(EventType.ItemDeleted.name())) {

        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

    }
}
