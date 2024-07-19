package bg.mck.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class UpdateMaterialDTO {

    private String name;
    private String type;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private Double maxLength;
    private Double area;
    private Double thickness;
    private Double totalWeight;
    private Double width;
    private Double totalThickness;
    private Double FrontSheetThickness;
    private Double BackSheetThickness;
    private Double MaxLength;
    private Double galvanisedSheetThickness;
    private String color;

    public UpdateMaterialDTO() {
    }

    public String getName() {
        return name;
    }

    public UpdateMaterialDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateMaterialDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public UpdateMaterialDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public UpdateMaterialDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public UpdateMaterialDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UpdateMaterialDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateMaterialDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateMaterialDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateMaterialDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public UpdateMaterialDTO setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public UpdateMaterialDTO setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return color;
    }

    public UpdateMaterialDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public UpdateMaterialDTO setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public UpdateMaterialDTO setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public UpdateMaterialDTO setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public UpdateMaterialDTO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public UpdateMaterialDTO setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public UpdateMaterialDTO setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public UpdateMaterialDTO setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
        return this;
    }
}
