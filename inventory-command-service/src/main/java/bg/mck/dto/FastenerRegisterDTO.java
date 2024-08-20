package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class FastenerRegisterDTO extends BaseDTO {

    private String type;
    private String diameter;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;

    public String getType() {
        return type;
    }

    public FastenerRegisterDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerRegisterDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getLength() {
        return length;
    }

    public FastenerRegisterDTO setLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerRegisterDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerRegisterDTO setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerRegisterDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }
}
