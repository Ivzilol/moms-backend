package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SetRegisterDTO extends BaseDTO {

    @NotNull(message = "Color is required")
    private String color;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull(message = "Length is required")
    private String maxLength;
    @NotNull(message = "Length unit is required")
    private LengthUnits maxLengthUnit;

    public String getColor() {
        return color;
    }

    public SetRegisterDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetRegisterDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetRegisterDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }
}
