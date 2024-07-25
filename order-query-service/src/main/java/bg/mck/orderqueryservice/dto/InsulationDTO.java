package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class InsulationDTO extends BaseDTO{

    private String type;

    private String thickness;
    private LengthUnits thicknessUnit;

    public InsulationDTO() {
    }

    public InsulationDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String thickness, LengthUnits thicknessUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.thickness = thickness;
        this.thicknessUnit = thicknessUnit;
    }

    public String getType() {
        return type;
    }

    public InsulationDTO setType(String type) {
        this.type = type;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Thickness must be positive") String getThickness() {
        return thickness;
    }

    public InsulationDTO setThickness(@Pattern(regexp = "^[^-].*", message = "Thickness must be positive") String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }
}
