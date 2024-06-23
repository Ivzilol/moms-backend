package bg.mck.userqueryservice.application.events;

import bg.mck.userqueryservice.application.enums.EventType;

import java.time.LocalDateTime;

public class PasswordUpdateEvent extends BaseEvent{

    private String newPassword;

    public PasswordUpdateEvent() {
    }

    public PasswordUpdateEvent(EventType eventType, Long userId, LocalDateTime localDateTime, String newPassword) {
        super(eventType, userId, localDateTime);
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordUpdateEvent setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
