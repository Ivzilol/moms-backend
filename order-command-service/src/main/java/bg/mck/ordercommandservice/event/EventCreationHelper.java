package bg.mck.ordercommandservice.event;

public class EventCreationHelper {

    public static <T extends BaseEvent> EventData<T> toOrderEvent(T event) {
        EventData<T> orderEvent = new EventData<>();
        orderEvent.setEventType(event.getEventType());
        orderEvent.setEvent(event);
        return orderEvent;
    }

}
