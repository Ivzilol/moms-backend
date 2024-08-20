package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class RebarRegisterDTO extends BaseDTO {

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getMaxLength() {
        return maxLength;
    }

    public RebarRegisterDTO setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarRegisterDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }
}
