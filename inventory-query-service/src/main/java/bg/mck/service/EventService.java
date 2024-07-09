package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.events.RegisterMaterialEvent;
import bg.mck.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final ObjectMapper objectMapper;

    private final MaterialService materialService;

    public EventService(EventRepository eventRepository, ObjectMapper objectMapper, MaterialService materialService) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
        this.materialService = materialService;
    }

    public void createMaterial(String data, String eventType) throws JsonProcessingException {
        if (eventType.equals(String.valueOf(EventType.MaterialRegister))) {
            MaterialEvent<RegisterMaterialEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterMaterialEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterMaterial(saveEvent.getEvent());
        }
    }



    private <T extends BaseEvent> MaterialEvent<T> saveEvent(MaterialEvent<T> materialEvent) {
        return this.eventRepository.save(materialEvent);
    }
}
