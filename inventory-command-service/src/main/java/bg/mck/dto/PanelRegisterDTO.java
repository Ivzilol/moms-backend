package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PanelRegisterDTO extends BaseDTO {

    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Color is required")
    private String color;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String length;
    private LengthUnits lengthUnit;
    @Pattern(regexp = "^[^-].*", message = "Width must be positive")
    private String width;
    private LengthUnits widthUnit;
    @Pattern(regexp = "^[^-].*", message = "TotalThickness must be positive")
    private String totalThickness;
    private LengthUnits totalThicknessUnit;
    @Pattern(regexp = "^[^-].*", message = "FrontSheetThickness must be positive")
    @NotNull(message = "Front sheet thickness is required")
    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    @Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive")
    @NotNull(message = "Back sheet thickness is required")
    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;

    public @NotNull(message = "Type is required") String getType() {
        return type;
    }

    public PanelRegisterDTO setType(@NotNull(message = "Type is required") String type) {
        this.type = type;
        return this;
    }

    public @NotNull(message = "Color is required") String getColor() {
        return color;
    }

    public PanelRegisterDTO setColor(@NotNull(message = "Color is required") String color) {
        this.color = color;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getLength() {
        return length;
    }

    public PanelRegisterDTO setLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public PanelRegisterDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Width must be positive") String getWidth() {
        return width;
    }

    public PanelRegisterDTO setWidth(@Pattern(regexp = "^[^-].*", message = "Width must be positive") String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public PanelRegisterDTO setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "TotalThickness must be positive") String getTotalThickness() {
        return totalThickness;
    }

    public PanelRegisterDTO setTotalThickness(@Pattern(regexp = "^[^-].*", message = "TotalThickness must be positive") String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public PanelRegisterDTO setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "FrontSheetThickness must be positive") @NotNull(message = "Front sheet thickness is required") String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelRegisterDTO setFrontSheetThickness(@Pattern(regexp = "^[^-].*", message = "FrontSheetThickness must be positive") @NotNull(message = "Front sheet thickness is required") String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public PanelRegisterDTO setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive") @NotNull(message = "Back sheet thickness is required") String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelRegisterDTO setBackSheetThickness(@Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive") @NotNull(message = "Back sheet thickness is required") String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public PanelRegisterDTO setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }
}
