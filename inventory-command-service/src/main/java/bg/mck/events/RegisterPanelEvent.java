package bg.mck.events;

import bg.mck.enums.EventType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class RegisterPanelEvent extends BaseEvent{

    private String category;

    private String name;

    private String type;

    private String color;

    private Double length;

    private Double width;

    private Double totalThickness;

    private Double FrontSheetThickness;

    private Double BackSheetThickness;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RegisterPanelEvent(Long materialId, EventType eventType, String category, String name, String type, String color, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness, Double quantity, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        FrontSheetThickness = frontSheetThickness;
        BackSheetThickness = backSheetThickness;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterPanelEvent() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public void setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public void setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
