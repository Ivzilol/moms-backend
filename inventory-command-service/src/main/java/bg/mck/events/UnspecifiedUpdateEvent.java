package bg.mck.events;

import bg.mck.enums.EventType;

public class UnspecifiedUpdateEvent extends BaseEvent {

    public UnspecifiedUpdateEvent() {
    }

    public UnspecifiedUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }


    private String category;
    private String materialType;
    private String description;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public UnspecifiedUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UnspecifiedUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UnspecifiedUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
