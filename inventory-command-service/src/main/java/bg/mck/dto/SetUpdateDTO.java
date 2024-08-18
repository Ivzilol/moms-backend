package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SetUpdateDTO {


    private String name;

    @NotNull(message = "Color is required")
    private String color;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull(message = "Length is required")
    private String maxLength;
    @NotNull(message = "Length unit is required")
    private LengthUnits maxLengthUnit;

    private String description;

    private String specificationFileUrl;

    public SetUpdateDTO() {
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
