package bg.mck.orderqueryservice.events;

public class EventCreationHelper {

    public static <T extends BaseEvent> OrderEvent<T> toOrderEvent(T event) {
        OrderEvent<T> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(event.getEventType());
        orderEvent.setEvent(event);
        return orderEvent;
    }

}
