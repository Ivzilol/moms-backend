//package bg.mck.userqueryservice.application.service;
//
//
//import bg.mck.userqueryservice.application.enums.EventType;
//import bg.mck.userqueryservice.application.events.ProfileUpdatedEvent;
//import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
//import bg.mck.userqueryservice.application.events.UserEvent;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class KafkaListenerService {
//
//    private final ObjectMapper objectMapper;
//    private final EventService eventService;
//    private final UserRegistrationService userRegistrationService;
//
//    public KafkaListenerService(ObjectMapper objectMapper, UserRegistrationService userRegistrationService, EventService eventService) {
//        this.eventService = eventService;
//        this.objectMapper = objectMapper;
//        this.userRegistrationService = userRegistrationService;
//    }
//
//    @KafkaListener(topics = "${KAFKA.USER.TOPIC.NAME}", groupId = "user-query-service")
//    public void processUserEvent(@Header(KafkaHeaders.RECEIVED_KEY) String eventType, @Payload String data) throws JsonProcessingException {
//
//        if (eventType.equals(EventType.UserProfileUpdated.name())) {
//            UserEvent<ProfileUpdatedEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
//            });
//            eventService.saveEvent(userEvent);
//            eventService.reconstructUserEntity(userEvent.getEvent().getUserId());
//
//        } else if (eventType.equals(EventType.UserRegistered.name())) {
//            UserEvent<RegisteredUserEvent> userEvent = objectMapper.readValue(data, new TypeReference<>() {
//            });
//            UserEvent<RegisteredUserEvent> savedEvent = eventService.saveEvent(userEvent);
//            userRegistrationService.processUserRegister(savedEvent.getEvent());
//
//        } else {
//            throw new IllegalArgumentException("Unknown event type: " + eventType);
//        }
//
//    }
//
//}
//
//
