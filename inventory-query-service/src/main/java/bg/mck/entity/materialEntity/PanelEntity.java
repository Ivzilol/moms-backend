package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "panels")
public class PanelEntity {

    private String id;

    private String name;

    private String type;

    private String color;

    private Double length;

    private Double width;

    private Double totalThickness;

    private Double frontSheetThickness;

    private Double backSheetThickness;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public PanelEntity() {
    }

    public PanelEntity(String id, String name, String type, String color, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        this.frontSheetThickness = frontSheetThickness;
        this.backSheetThickness = backSheetThickness;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return frontSheetThickness;
    }

    public void setFrontSheetThickness(Double frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
    }

    public Double getBackSheetThickness() {
        return backSheetThickness;
    }

    public void setBackSheetThickness(Double backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
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
}