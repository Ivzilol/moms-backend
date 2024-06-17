package bg.mck.userqueryservice.application.events;

import bg.mck.userqueryservice.application.enums.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "user_events")
public class UserEvent<T extends BaseEvent> {

    @Id
    private String id;
    private EventType eventType;
    private T event;

    public UserEvent() {
    }

    public UserEvent(String id, EventType eventType, T event) {
        this.id = id;
        this.eventType = eventType;
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public UserEvent<T> setId(String id) {
        this.id = id;
        return this;
    }

    public EventType getEventType() {
        return eventType;
    }

    public UserEvent<T> setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public T getEvent() {
        return event;
    }

    public UserEvent<T> setEvent(T event) {
        this.event = event;
        return this;
    }
}
