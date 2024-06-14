package bg.mck.usercommandservice.application.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic userManagementTopic() {
        return TopicBuilder
                .name("userManagementTopic")
                .build();
    }
}
