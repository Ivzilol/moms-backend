package bg.mck.dto;

import jakarta.validation.constraints.DecimalMin;

public class SetUpdateDTO {

    private String name;

    @DecimalMin(value = "0.0", message = "GalvanisedSheetThickness must be positive")
    private Double galvanisedSheetThickness;
    private String color;
    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    private String maxLength;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;


    private String specificationFileUrl;

    public SetUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public SetUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetUpdateDTO setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetUpdateDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetUpdateDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
