package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InsulationUpdateDTO {

    private String name;

    @NotNull(message = "Type is required")
    private String type;

    @Pattern(regexp = "^[^-].*", message = "Thickness must be positive")
    private String thickness;

    private LengthUnits thicknessUnit;

    private String description;

    private String specificationFileUrl;

    public InsulationUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public InsulationUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationUpdateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationUpdateDTO setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationUpdateDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
