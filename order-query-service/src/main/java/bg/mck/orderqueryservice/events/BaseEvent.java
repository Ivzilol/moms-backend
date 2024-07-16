package bg.mck.orderqueryservice.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateOrderEvent.class, name = "CreateOrderEvent")
        // Add other subclasses here if needed
})
public abstract class BaseEvent {

    private Long orderId;
    private OrderEventType eventType;
    private LocalDateTime eventTime;

    public BaseEvent() {
    }

    public BaseEvent(OrderEventType eventType, Long orderId, LocalDateTime eventTime ) {
        this.eventType = eventType;
        this.orderId = orderId;
        this.eventTime = eventTime;
    }



    public OrderEventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(OrderEventType eventType) {
        this.eventType = eventType;
        return this;
    }


    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public BaseEvent setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BaseEvent setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
}
