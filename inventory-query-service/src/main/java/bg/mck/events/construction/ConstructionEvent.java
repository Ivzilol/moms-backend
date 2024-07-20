package bg.mck.events.construction;

import bg.mck.enums.EventType;


public class ConstructionEvent<T extends BaseConstructionEvent> {

    private EventType eventType;

    private T event;

    public ConstructionEvent() {
    }

    public ConstructionEvent(EventType eventType, T event) {
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
