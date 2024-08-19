package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

public class RebarUpdateDTO {

    private String name;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String description;

    private String specificationFileUrl;

    public RebarUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public RebarUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarUpdateDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarUpdateDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public RebarUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
