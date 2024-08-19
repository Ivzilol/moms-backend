package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class FastenerUpdateDTO {
    private String name;

    private String type;

    private String diameter;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String length;
    private LengthUnits lengthUnit;

    private String standard;

    private String clazz;

    private String description;

    private String specificationFileUrl;

    public FastenerUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public FastenerUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public FastenerUpdateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerUpdateDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FastenerUpdateDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerUpdateDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerUpdateDTO setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerUpdateDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public FastenerUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
