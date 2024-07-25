package bg.mck.orderqueryservice.dto;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class PanelDTO extends BaseDTO {

    private String type;
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
    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    @Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive")
    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;

    public PanelDTO() {
    }

    public PanelDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.color = color;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.width = width;
        this.widthUnit = widthUnit;
        this.totalThickness = totalThickness;
        this.totalThicknessUnit = totalThicknessUnit;
        this.frontSheetThickness = frontSheetThickness;
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        this.backSheetThickness = backSheetThickness;
        this.backSheetThicknessUnit = backSheetThicknessUnit;
    }

    public String getType() {
        return type;
    }

    public PanelDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getLength() {
        return length;
    }

    public PanelDTO setLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public PanelDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Width must be positive") String getWidth() {
        return width;
    }

    public PanelDTO setWidth(@Pattern(regexp = "^[^-].*", message = "Width must be positive") String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public PanelDTO setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "TotalThickness must be positive") String getTotalThickness() {
        return totalThickness;
    }

    public PanelDTO setTotalThickness(@Pattern(regexp = "^[^-].*", message = "TotalThickness must be positive") String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public PanelDTO setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "FrontSheetThickness must be positive") String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelDTO setFrontSheetThickness(@Pattern(regexp = "^[^-].*", message = "FrontSheetThickness must be positive") String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public PanelDTO setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive") String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelDTO setBackSheetThickness(@Pattern(regexp = "^[^-].*", message = "BackSheetThickness must be positive") String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public PanelDTO setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }
}
