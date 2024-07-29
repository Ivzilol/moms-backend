package bg.mck.ordercommandservice.event;

public class eventData<T extends BaseEvent> {

    private EventType eventType;

    private T event;

    public eventData() {
    }

    public eventData(EventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public eventData<T> setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public T getEvent() {
        return event;
    }

    public eventData<T> setEvent(T event) {
        this.event = event;
        return this;
    }
}
