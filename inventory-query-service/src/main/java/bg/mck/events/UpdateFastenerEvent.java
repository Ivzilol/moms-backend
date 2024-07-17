package bg.mck.events;

import bg.mck.enums.EventType;

public class UpdateFastenerEvent extends BaseEvent{

    private String name;
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String type;
    private String specificationFileUrl;

    public UpdateFastenerEvent() {
    }

    public UpdateFastenerEvent(Long materialId, EventType eventType, String name, String description, String diameter, Double length, String model, String clazz, Double quantity, String type, String specificationFileUrl) {
        super(materialId, eventType);
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        this.type = type;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getName() {
        return name;
    }

    public UpdateFastenerEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateFastenerEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public UpdateFastenerEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public UpdateFastenerEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public UpdateFastenerEvent setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UpdateFastenerEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateFastenerEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateFastenerEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateFastenerEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}