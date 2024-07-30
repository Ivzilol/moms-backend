package bg.mck.ordercommandservice.event;

public class EventData<T extends BaseEvent> {

    private EventType eventType;

    private T event;

    public EventData() {
    }

    public EventData(EventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventData<T> setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public T getEvent() {
        return event;
    }

    public EventData<T> setEvent(T event) {
        this.event = event;
        return this;
    }
}
