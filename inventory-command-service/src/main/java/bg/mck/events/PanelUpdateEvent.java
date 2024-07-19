package bg.mck.events;

import bg.mck.enums.EventType;
import jakarta.persistence.Column;

public class PanelUpdateEvent extends BaseEvent {

    public PanelUpdateEvent() {
    }

    public PanelUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String category;

    private String materialType;
    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double sheetThickness;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public PanelUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getType() {
        return type;
    }

    public PanelUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelUpdateEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public PanelUpdateEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelUpdateEvent setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelUpdateEvent setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public PanelUpdateEvent setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public PanelUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public PanelUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
