package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateUnspecifiedEvent extends BaseMaterialEvent {

    public UpdateUnspecifiedEvent() {
    }

    public UpdateUnspecifiedEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }


    public UpdateUnspecifiedEvent(Long materialId, EventType eventType, String category, String name, String materialType, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.materialType = materialType;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    private String category;
    private String name;
    private String materialType;
    private String description;
    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public UpdateUnspecifiedEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateUnspecifiedEvent setDescription(String description) {
        this.description = description;
        return this;
    }



    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateUnspecifiedEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateUnspecifiedEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateUnspecifiedEvent setName(String name) {
        this.name = name;
        return this;
    }
}
