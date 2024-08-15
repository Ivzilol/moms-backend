package bg.mck.dto;

import bg.mck.enums.WeightUnits;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class MetalUpdateDTO {
    private String name;

    private String totalWeight;

    private WeightUnits totalWeightUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public MetalUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public MetalUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalUpdateDTO setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalUpdateDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
