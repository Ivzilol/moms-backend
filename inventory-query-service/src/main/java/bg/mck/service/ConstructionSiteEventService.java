package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import bg.mck.repository.constructionSite.EventConstructionSiteRepository;
import bg.mck.repository.transport.EventTransportRepository;
import bg.mck.repository.transport.TransportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConstructionSiteEventService {


    private final ObjectMapper objectMapper;
    private final EventConstructionSiteRepository eventConstructionRepository;
    private final ConstructionSiteRepository constructionRepository;

    public ConstructionSiteEventService(ObjectMapper objectMapper, EventConstructionSiteRepository eventConstructionRepository, ConstructionSiteRepository constructionRepository) {
        this.objectMapper = objectMapper;
        this.eventConstructionRepository = eventConstructionRepository;
        this.constructionRepository = constructionRepository;
    }


    public void processConstructionEvent(String data, String eventType) {
        if (eventType.equals(EventType.ItemRegistered.name())) {

        } else if (eventType.equals(EventType.ItemDeleted.name())) {

        } else if (eventType.equals(EventType.ItemUpdated.name())) {

        } else {
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

    }
}
