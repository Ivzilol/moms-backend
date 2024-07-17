package bg.mck.events;

import bg.mck.enums.EventType;

public class MaterialDeletedEvent extends BaseEvent {

    private String name;

    public MaterialDeletedEvent() {

    }

    public MaterialDeletedEvent(Long materialId, EventType eventType, String name) {
        super(materialId, eventType);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public MaterialDeletedEvent setName(String name) {
        this.name = name;
        return this;
    }
}
