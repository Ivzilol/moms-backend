package bg.mck.orderqueryservice.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_events")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateOrderEvent.class, name = "ORDER_CREATED"),
        // Add more subtypes as needed
})
public class OrderEvent<E extends BaseEvent> {

    @Id
    private String id;
    private OrderEventType eventType;
    private E event;


    public OrderEvent() {
    }


    public OrderEvent(String id, OrderEventType eventType, E event) {
        this.id = id;
        this.eventType = eventType;
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public OrderEvent<E> setId(String id) {
        this.id = id;
        return this;
    }

    public OrderEventType getEventType() {
        return eventType;
    }

    public OrderEvent<E> setEventType(OrderEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public E getEvent() {
        return event;
    }

    public OrderEvent<E> setOrder(E event) {
        this.event = event;
        return this;
    }

    public OrderEvent<E> setEvent(E event) {
        this.event = event;
        return this;
    }
}
