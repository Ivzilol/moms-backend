package bg.mck.usercommandservice.application.dto;

import bg.mck.usercommandservice.application.entity.UserCommandEntity;

public class UserEvent {

    private String eventType;
    private UserCommandEntity userCommandEntity;

    public UserEvent(String eventType, UserCommandEntity userCommandEntity) {
        this.eventType = eventType;
        this.userCommandEntity = userCommandEntity;
    }

    public UserEvent() {
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public UserCommandEntity getUserCommandEntity() {
        return userCommandEntity;
    }

    public void setUserCommandEntity(UserCommandEntity userCommandEntity) {
        this.userCommandEntity = userCommandEntity;
    }
}
