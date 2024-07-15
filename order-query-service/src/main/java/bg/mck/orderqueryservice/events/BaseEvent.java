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
    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(OrderEventType eventType, Long orderId, LocalDateTime localDateTime ) {
        this.eventType = eventType;
        this.orderId = orderId;
        this.localDateTime = localDateTime;
    }



    public OrderEventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(OrderEventType eventType) {
        this.eventType = eventType;
        return this;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public BaseEvent setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
