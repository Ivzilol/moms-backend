package bg.mck.orderqueryservice.dto;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class SetDTO extends BaseDTO {

    @Pattern(regexp = "^[^-].*", message = "GalvanisedSheetThickness must be positive")
    private String galvanisedSheetThickness;
    private LengthUnits galvanisedSheetThicknessUnit;
    private String color;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;

    public SetDTO() {
    }

    public SetDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String galvanisedSheetThickness, LengthUnits galvanisedSheetThicknessUnit, String color, String maxLength, LengthUnits maxLengthUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetDTO setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public SetDTO setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }
}
