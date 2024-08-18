package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.AreaUnits;
import bg.mck.orderqueryservice.entity.enums.LengthUnits;

public class PanelDTO extends BaseDTO {

    private String type;
    private String color;

    private String length;
    private LengthUnits lengthUnit;

    private String width;
    private LengthUnits widthUnit;

    private String totalThickness;
    private LengthUnits totalThicknessUnit;

    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;

    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;

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

    public PanelDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit, String quantity, AreaUnits quantityUnit) {
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

    public String getColor() {
        return color;
    }

    public PanelDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public PanelDTO setLength(String length) {
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

    public String getWidth() {
        return width;
    }

    public PanelDTO setWidth(String width) {
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

    public String getTotalThickness() {
        return totalThickness;
    }

    public PanelDTO setTotalThickness(String totalThickness) {
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

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelDTO setFrontSheetThickness(String frontSheetThickness) {
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

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelDTO setBackSheetThickness(String backSheetThickness) {
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

    public String getQuantity() {
        return quantity;
    }

    public PanelDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public AreaUnits getQuantityUnit() {
        return quantityUnit;
    }

    public PanelDTO setQuantityUnit(AreaUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }
}
