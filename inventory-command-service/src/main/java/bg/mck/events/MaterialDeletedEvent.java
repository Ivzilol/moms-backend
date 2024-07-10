package bg.mck.events;

import bg.mck.enums.EventType;

public class MaterialDeletedEvent extends BaseEvent {

    public MaterialDeletedEvent() {

    }

    public MaterialDeletedEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
}
