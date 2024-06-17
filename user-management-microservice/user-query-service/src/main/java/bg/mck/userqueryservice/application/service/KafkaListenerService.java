package bg.mck.userqueryservice.application.service;


import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.ProfileUpdatedEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import bg.mck.userqueryservice.application.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;



@Service
public class KafkaListenerService {

    private final EventRepository eventRepository;
    private final UserProfileManagementService profileManagementService;
    private final ObjectMapper objectMapper;

    public KafkaListenerService(EventRepository eventRepository, UserProfileManagementService profileManagementService, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.profileManagementService = profileManagementService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${KAFKA.USER.TOPIC.NAME}", groupId = "user-query-service")
    public void processUserEvent(@Header(KafkaHeaders.RECEIVED_KEY) String eventType, @Payload String data) throws JsonProcessingException {
        UserEvent<?> newEvent;
        if (eventType.equals(EventType.UserProfileUpdated.name())) {
            UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {});
            profileManagementService.updateUserProfile(userEvent.getEvent());
            newEvent = userEvent;
        } else {
            throw new IllegalArgumentException("Unknown event type: " + eventType);
        }

        eventRepository.save(newEvent);
    }


}


