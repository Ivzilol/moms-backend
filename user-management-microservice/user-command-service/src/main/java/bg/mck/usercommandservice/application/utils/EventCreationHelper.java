package bg.mck.usercommandservice.application.utils;

import bg.mck.usercommandservice.application.events.BaseEvent;
import bg.mck.usercommandservice.application.events.UserEvent;

public class EventCreationHelper {


   public static <T extends BaseEvent> UserEvent<T> toUserEvent(T event) {
       UserEvent<T> userEvent = new UserEvent<>();
       userEvent.setEventType(event.getEventType());
       userEvent.setEvent(event);
       return userEvent;
    }
}
