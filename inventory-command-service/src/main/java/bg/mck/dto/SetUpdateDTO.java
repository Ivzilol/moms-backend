package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;

public class SetUpdateDTO {


    private String name;
    private String galvanisedSheetThickness;

    private LengthUnits galvanisedSheetThicknessUnit;

    private String color;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public SetUpdateDTO() {
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetUpdateDTO setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public SetUpdateDTO setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
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

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetUpdateDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
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

    public String getName() {
        return name;
    }

    public SetUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }
}
