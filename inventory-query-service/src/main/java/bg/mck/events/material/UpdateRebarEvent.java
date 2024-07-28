package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateRebarEvent extends BaseMaterialEvent {

    public UpdateRebarEvent() {
    }

    public UpdateRebarEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String category;

    private String name;
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

    public UpdateRebarEvent setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateRebarEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getSteel() {
        return steel;
    }

    public UpdateRebarEvent setSteel(String steel) {
        this.steel = steel;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public UpdateRebarEvent setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public UpdateRebarEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public UpdateRebarEvent setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateRebarEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UpdateRebarEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateRebarEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdateRebarEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateRebarEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateRebarEvent setName(String name) {
        this.name = name;
        return this;
    }
}
