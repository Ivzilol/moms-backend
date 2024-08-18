package bg.mck.dto;

import bg.mck.enums.WeightUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MetalRegisterDTO extends BaseDTO{

    @Pattern(regexp = "^[^-].*", message = "Weight must be positive")
    @NotNull(message = "Total weight is required")
    private String totalWeight;
    private WeightUnits totalWeightUnit;
    @NotNull(message = "Kind is required")
    private String kind;

    public @Pattern(regexp = "^[^-].*", message = "Weight must be positive") @NotNull(message = "Total weight is required") String getTotalWeight() {
        return totalWeight;
    }

    public MetalRegisterDTO setTotalWeight(@Pattern(regexp = "^[^-].*", message = "Weight must be positive") @NotNull(message = "Total weight is required") String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalRegisterDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public @NotNull(message = "Kind is required") String getKind() {
        return kind;
    }

    public MetalRegisterDTO setKind(@NotNull(message = "Kind is required") String kind) {
        this.kind = kind;
        return this;
    }
}
