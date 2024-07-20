package bg.mck.events.construction;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseConstructionEvent {

    private String constructionId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseConstructionEvent() {
    }

    public BaseConstructionEvent(String constructionId, EventType eventType) {
        this.constructionId = constructionId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
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

    public String getConstructionId() {
        return constructionId;
    }

    public BaseConstructionEvent setConstructionId(String constructionId) {
        this.constructionId = constructionId;
        return this;
    }
}
