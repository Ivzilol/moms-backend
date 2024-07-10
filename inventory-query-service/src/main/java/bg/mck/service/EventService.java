package bg.mck.service;

import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.*;
import bg.mck.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import static bg.mck.service.MaterialService.extractCategoryString;


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

        if (eventType.equals(String.valueOf(EventType.MaterialRegister)) &&
            category.equals(String.valueOf(MaterialType.INSULATION))) {
            MaterialEvent<RegisterInsulationEvent> materialEvent =
                    objectMapper.readValue(data, new TypeReference<>() {
                    });
            MaterialEvent<RegisterInsulationEvent> saveEvent = saveEvent(materialEvent);
            this.materialService.processingRegisterInsulation(saveEvent.getEvent());
        }

    }


    private <T extends BaseEvent> MaterialEvent<T> saveEvent(MaterialEvent<T> materialEvent) {
        return this.eventRepository.save(materialEvent);
    }
}
