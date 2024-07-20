package bg.mck.events.transport;

import bg.mck.enums.EventType;


public class TransportEvent<T extends BaseTransportEvent> {

    private EventType eventType;

    private T event;

    public TransportEvent() {
    }

    public TransportEvent(EventType eventType, T event) {
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
