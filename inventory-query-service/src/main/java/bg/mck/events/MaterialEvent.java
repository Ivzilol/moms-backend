package bg.mck.events;

import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;

public class MaterialEvent<T extends BaseEvent> {

    private EventType eventType;

    private MaterialType materialType;

    private T event;

    public MaterialEvent() {
    }

    public MaterialEvent(EventType eventType, MaterialType materialType, T event) {
        this.eventType = eventType;
        this.materialType = materialType;
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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public MaterialEvent<T> setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }
}
