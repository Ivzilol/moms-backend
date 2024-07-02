package bg.mck.utils;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;

public class EventCreationHelper {

    public static <T extends BaseEvent> MaterialEvent<T> toMaterialEvent(T event) {
        MaterialEvent<T> materialEvent = new MaterialEvent<>();
        materialEvent.setEventType(event.getEventType());
        materialEvent.setEvent(event);
        return materialEvent;
    }

}
