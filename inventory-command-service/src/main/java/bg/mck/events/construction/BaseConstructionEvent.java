package bg.mck.events.construction;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseConstructionEvent {

    private Long constructionId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseConstructionEvent() {
    }

    public BaseConstructionEvent(Long constructionId, EventType eventType) {
        this.constructionId = constructionId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Long constructionId) {
        this.constructionId = constructionId;
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
