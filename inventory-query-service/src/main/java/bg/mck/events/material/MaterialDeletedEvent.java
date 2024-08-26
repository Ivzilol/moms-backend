package bg.mck.events.material;

import bg.mck.enums.EventType;

public class MaterialDeletedEvent extends BaseMaterialEvent {

    private String name;
    private String category;

    public MaterialDeletedEvent() {

    }

    public MaterialDeletedEvent(Long materialId, EventType eventType, String name, String category) {
        super(materialId, eventType);
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public MaterialDeletedEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public MaterialDeletedEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
