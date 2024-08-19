package bg.mck.dto;

import bg.mck.enums.WeightUnits;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MetalUpdateDTO {
    private String name;

    @Pattern(regexp = "^[^-].*", message = "Weight must be positive")
    @NotNull(message = "Total weight is required")
    private String totalWeight;

    private WeightUnits totalWeightUnit;
    @NotNull(message = "Kind is required")
    private String kind;
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

    public String getKind() {
        return kind;
    }

    public MetalUpdateDTO setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
