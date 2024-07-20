package bg.mck.events.material;

import bg.mck.enums.EventType;

public class RebarUpdateEvent extends BaseMaterialEvent {

    public RebarUpdateEvent() {
    }

    public RebarUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String category;

    private String materialType;
    private Integer positionNumber;
    private String type;
    private String steel;
    private Double diameter;
    private Double length;
    private Double weight;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public RebarUpdateEvent setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    public String getType() {
        return type;
    }

    public RebarUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getSteel() {
        return steel;
    }

    public RebarUpdateEvent setSteel(String steel) {
        this.steel = steel;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public RebarUpdateEvent setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public RebarUpdateEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarUpdateEvent setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RebarUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public RebarUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RebarUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
