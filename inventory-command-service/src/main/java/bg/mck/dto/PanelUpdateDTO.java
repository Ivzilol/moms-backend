package bg.mck.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class PanelUpdateDTO {

    private String name;

    @DecimalMin(value = "0.0", message = "Length must be positive")
    private Double length;
    @DecimalMin(value = "0.0", message = "Width must be positive")
    private Double width;
    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    private Double totalThickness;
    @DecimalMin(value = "0.0", message = "FrontSheetThickness must be positive")
    private Double FrontSheetThickness;
    @DecimalMin(value = "0.0", message = "BackSheetThickness must be positive")
    private Double BackSheetThickness;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;


    private String specificationFileUrl;

    public PanelUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public PanelUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public PanelUpdateDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelUpdateDTO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelUpdateDTO setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public PanelUpdateDTO setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public PanelUpdateDTO setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PanelUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
