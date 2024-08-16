package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;
import jakarta.validation.constraints.DecimalMin;

public class RebarUpdateDTO {

    private String name;
    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String weight;

    private WeightUnits weightUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

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

    public String getWeight() {
        return weight;
    }

    public RebarUpdateDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public RebarUpdateDTO setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
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
