package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.events.BaseEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {

    private final KafkaTemplate<String, BaseEvent> kafkaTemplate;
    private final NewTopic userTopic;

    public KafkaPublisherService(KafkaTemplate<String, BaseEvent> kafkaTemplate, NewTopic userTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.userTopic = userTopic;
    }

    public void publishToTopic(String topic, BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }

    public void publish(BaseEvent event) {
        kafkaTemplate.send(userTopic.name(), event);
    }


}
