package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static bg.mck.ordercommandservice.dto.util.RegexPatterns.POSITIVE_NUMBER_REGEX;

public class PanelDTO extends BaseDTO {

    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Color is required")
    private String color;

    @Pattern(regexp = POSITIVE_NUMBER_REGEX, message = "Length must be numeric and positive")
    private String length;
    private LengthUnits lengthUnit;
    @Pattern(regexp = POSITIVE_NUMBER_REGEX, message = "must be numeric and positive")
    @NotNull(message = "Width is required")
    private String width;
    private LengthUnits widthUnit;
    @Pattern(regexp = POSITIVE_NUMBER_REGEX, message = "TotalThickness must be numeric and positive")
    private String totalThickness;
    private LengthUnits totalThicknessUnit;
    @Pattern(regexp = POSITIVE_NUMBER_REGEX, message = "FrontSheetThickness must be numeric and positive")
    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    @Pattern(regexp = POSITIVE_NUMBER_REGEX, message = "BackSheetThickness must be numeric and positive")
    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;
    @NotNull(message = "Quantity is required")
    private String quantity;
    private AreaUnits quantityUnit;

    public PanelDTO() {
    }

    public PanelDTO(String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit, String quantity, AreaUnits quantityUnit) {
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
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public PanelDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit, String quantity, AreaUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
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
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public String getType() {
        return type;
    }

    public PanelDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public PanelDTO setQuantity(String quantity) {
        this.quantity = quantity;
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

    public AreaUnits getQuantityUnit() {
        return quantityUnit;
    }

    public PanelDTO setQuantityUnit(AreaUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
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
