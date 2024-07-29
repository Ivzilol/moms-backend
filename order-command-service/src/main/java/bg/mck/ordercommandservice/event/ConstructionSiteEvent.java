package bg.mck.ordercommandservice.event;

import java.time.LocalDateTime;

public class ConstructionSiteEvent extends BaseEvent {
    private Long id;
    private String constructionNumber;
    private String name;

    public ConstructionSiteEvent() {
    }

    public ConstructionSiteEvent(Long orderId, EventType eventType, LocalDateTime eventTime, Long id, String constructionNumber, String name) {
        super(orderId, eventType, eventTime);
        this.id = id;
        this.constructionNumber = constructionNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ConstructionSiteEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public ConstructionSiteEvent setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConstructionSiteEvent setName(String name) {
        this.name = name;
        return this;
    }
}
