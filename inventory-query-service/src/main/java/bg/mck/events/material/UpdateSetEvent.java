package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateSetEvent extends BaseMaterialEvent {

    public UpdateSetEvent() {
    }

    public UpdateSetEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String category;
    private String name;
    private String materialType;
    private String description;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public UpdateSetEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateSetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateSetEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UpdateSetEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateSetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateSetEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateSetEvent setName(String name) {
        this.name = name;
        return this;
    }
}
