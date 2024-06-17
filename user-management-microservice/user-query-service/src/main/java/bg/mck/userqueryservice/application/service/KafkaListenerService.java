package bg.mck.userqueryservice.application.service;


import bg.mck.userqueryservice.application.enums.EventType;
import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.ProfileUpdatedEvent;
import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
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
    private final ObjectMapper objectMapper;
    private final UserProfileManagementService profileManagementService;
    private final UserRegistrationService userRegistrationService;

    public KafkaListenerService(EventRepository eventRepository, UserProfileManagementService profileManagementService, ObjectMapper objectMapper, UserRegistrationService userRegistrationService) {
        this.eventRepository = eventRepository;
        this.profileManagementService = profileManagementService;
        this.objectMapper = objectMapper;
        this.userRegistrationService = userRegistrationService;
    }

    @KafkaListener(topics = "${KAFKA.USER.TOPIC.NAME}", groupId = "user-query-service")
    public void processUserEvent(@Header(KafkaHeaders.RECEIVED_KEY) String eventType, @Payload String data) throws JsonProcessingException {
        UserEvent<?> newEvent;
        if (eventType.equals(EventType.UserProfileUpdated.name())) {
            UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {});
            profileManagementService.updateUserProfile(userEvent.getEvent());
            newEvent = userEvent;
        } else if (eventType.equals(EventType.UserRegistered.name())) {
            UserEvent<RegisteredUserEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {});
            userRegistrationService.processUserRegister(userEvent.getEvent());
            newEvent = userEvent;
        } else {
            throw new IllegalArgumentException("Unknown event type: " + eventType);
        }

        eventRepository.save(newEvent);
    }


}


