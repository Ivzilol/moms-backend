package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MetalDTO extends BaseDTO {

    @Pattern(regexp = "^[^-].*", message = "Weight must be positive")
    @NotNull(message = "Total weight is required")
    private String totalWeight;
    private WeightUnits totalWeightUnit;
    @NotNull(message = "Kind is required")
    private String kind;

    public MetalDTO() {
    }

    public MetalDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String totalWeight, WeightUnits totalWeightUnit, String kind) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
        this.kind = kind;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalDTO setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public MetalDTO setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
