package bg.mck.events.material;

import bg.mck.enums.EventType;

public class FastenerUpdateEvent extends BaseMaterialEvent {

    private String category;
    private String materialType;
    private String name;
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public FastenerUpdateEvent() {
    }

    public FastenerUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public String getMaterialType() {
        return materialType;
    }

    public FastenerUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getName() {
        return name;
    }

    public FastenerUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FastenerUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerUpdateEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public FastenerUpdateEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FastenerUpdateEvent setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerUpdateEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public FastenerUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public FastenerUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public FastenerUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
