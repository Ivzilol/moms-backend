package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.repository.service.EventServiceRepository;
import bg.mck.repository.service.ServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceEventService {


    private final ObjectMapper objectMapper;
    private final EventServiceRepository eventServiceRepository;
    private final ServiceRepository serviceRepository;


    public ServiceEventService(ObjectMapper objectMapper, EventServiceRepository eventServiceRepository, ServiceRepository serviceRepository) {
        this.objectMapper = objectMapper;
        this.eventServiceRepository = eventServiceRepository;
        this.serviceRepository = serviceRepository;
    }


    public void processMaterialEvent(String data, String eventType) {
        if (eventType.equals(EventType.ItemRegistered.name())) {

        } else if (eventType.equals(EventType.ItemDeleted.name())) {

        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

    }
}
