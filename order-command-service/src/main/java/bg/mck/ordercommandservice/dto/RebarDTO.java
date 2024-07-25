package bg.mck.ordercommandservice.dto;


import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import jakarta.validation.constraints.Pattern;

public class RebarDTO extends BaseDTO {

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Weight must be positive")
    private String weight;
    private WeightUnits weightUnit;

    public RebarDTO() {
    }

    public RebarDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getMaxLength() {
        return maxLength;
    }

    public RebarDTO setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Weight must be positive") String getWeight() {
        return weight;
    }

    public RebarDTO setWeight(@Pattern(regexp = "^[^-].*", message = "Weight must be positive") String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public RebarDTO setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }
}
