package bg.mck.events;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private Long materialId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(Long materialId, EventType eventType) {
        this.materialId = materialId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
