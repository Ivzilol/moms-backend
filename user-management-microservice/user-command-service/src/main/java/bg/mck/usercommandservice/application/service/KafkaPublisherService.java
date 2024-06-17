package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.events.BaseEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final ObjectMapper objectMapper;

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate, @Value("${KAFKA.USER.TOPIC.NAME}") String topicName, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.objectMapper = objectMapper;
    }

    public <T extends BaseEvent> void publishUserEventToTopic(String topic, UserEvent<T> event) {
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends BaseEvent> void publishUserEvent(UserEvent<T> event) {
        try {
            kafkaTemplate.send(topicName, event.getEventType().name(), objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
