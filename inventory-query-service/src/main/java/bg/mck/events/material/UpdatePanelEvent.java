package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdatePanelEvent extends BaseMaterialEvent {

    public UpdatePanelEvent() {
    }

    public UpdatePanelEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String category;
    private String name;
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

    public UpdatePanelEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdatePanelEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public UpdatePanelEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public UpdatePanelEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public UpdatePanelEvent setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public UpdatePanelEvent setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public UpdatePanelEvent setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdatePanelEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UpdatePanelEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdatePanelEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdatePanelEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdatePanelEvent setName(String name) {
        this.name = name;
        return this;
    }
}
