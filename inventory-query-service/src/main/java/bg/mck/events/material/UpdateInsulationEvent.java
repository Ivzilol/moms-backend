package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateInsulationEvent extends BaseMaterialEvent {

    public UpdateInsulationEvent() {
    }

    public UpdateInsulationEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    private String category;
    private String name;
    private String materialType;
    private String type;
    private String color;
    private String length;
    private String width;
    private Double thickness;
    private Double thermalPerformance;
    private Double density;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public UpdateInsulationEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateInsulationEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public UpdateInsulationEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public UpdateInsulationEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public UpdateInsulationEvent setWidth(String width) {
        this.width = width;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public UpdateInsulationEvent setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public Double getThermalPerformance() {
        return thermalPerformance;
    }

    public UpdateInsulationEvent setThermalPerformance(Double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
        return this;
    }

    public Double getDensity() {
        return density;
    }

    public UpdateInsulationEvent setDensity(Double density) {
        this.density = density;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateInsulationEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UpdateInsulationEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateInsulationEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateInsulationEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateInsulationEvent setName(String name) {
        this.name = name;
        return this;
    }
}
