package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InsulationRegisterDTO  extends BaseDTO{

    @NotNull(message = "Type is required")
    private String type;
    @Pattern(regexp = "^[^-].*", message = "Thickness must be positive")
    private String thickness;
    private LengthUnits thicknessUnit;

    public String getType() {
        return type;
    }

    public InsulationRegisterDTO setType(String type) {
        this.type = type;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Thickness must be positive") String getThickness() {
        return thickness;
    }

    public InsulationRegisterDTO setThickness(@Pattern(regexp = "^[^-].*", message = "Thickness must be positive") String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationRegisterDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }
}
