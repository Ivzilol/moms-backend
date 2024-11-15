package bg.mck.events.material;

import bg.mck.enums.EventType;

public class RegisterUnspecifiedEvent extends BaseMaterialEvent {

    private String category;

    private String name;


    private String description;

    private String specificationFileUrl;


    public RegisterUnspecifiedEvent(Long materialId, EventType eventType, String category, String name, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterUnspecifiedEvent() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
