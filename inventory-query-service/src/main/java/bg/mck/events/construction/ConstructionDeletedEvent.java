package bg.mck.events.construction;

import bg.mck.enums.EventType;

public class ConstructionDeletedEvent extends BaseConstructionEvent {

    private String name;

    public ConstructionDeletedEvent() {
    }

    public ConstructionDeletedEvent(String constructionId, EventType eventType, String name) {
        super(constructionId, eventType);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ConstructionDeletedEvent setName(String name) {
        this.name = name;
        return this;
    }
}
