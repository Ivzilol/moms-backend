package bg.mck.events;

import bg.mck.enums.EventType;

public class MaterialEvent<T extends BaseEvent> {

    private EventType eventType;

    private T event;

    public MaterialEvent() {
    }

    public MaterialEvent(EventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }
}
