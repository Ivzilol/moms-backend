package bg.mck.ordercommandservice.event;


public class OrderEvent<T extends BaseEvent> {

    private OrderEventType eventType;

    private T event;

    public OrderEvent() {
    }

    public OrderEvent(OrderEventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public OrderEventType getEventType() {
        return eventType;
    }

    public OrderEvent<T> setEventType(OrderEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public T getEvent() {
        return event;
    }

    public OrderEvent<T> setEvent(T event) {
        this.event = event;
        return this;
    }
}
