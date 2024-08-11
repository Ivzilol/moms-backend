package bg.mck.events.construction;

import bg.mck.enums.EventType;

public class ConstructionRegisteredEvent extends BaseConstructionEvent {

    private String constructionNumber;
    private String name;

    public ConstructionRegisteredEvent() {
    }

    public ConstructionRegisteredEvent(Long constructionId, EventType eventType, String constructionNumber, String name) {
        super(constructionId, eventType);
        this.constructionNumber = constructionNumber;
        this.name = name;
    }

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public ConstructionRegisteredEvent setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConstructionRegisteredEvent setName(String name) {
        this.name = name;
        return this;
    }
}
