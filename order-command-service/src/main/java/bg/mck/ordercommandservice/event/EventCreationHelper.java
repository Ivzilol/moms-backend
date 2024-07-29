package bg.mck.ordercommandservice.event;

public class EventCreationHelper {

    public static <T extends BaseEvent> eventData<T> toOrderEvent(T event) {
        eventData<T> orderEvent = new eventData<>();
        orderEvent.setEventType(event.getEventType());
        orderEvent.setEvent(event);
        return orderEvent;
    }

}
