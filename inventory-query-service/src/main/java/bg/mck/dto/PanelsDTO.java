package bg.mck.dto;

import bg.mck.enums.LengthUnits;

public class PanelsDTO implements MaterialDTO{

    private String id;

    private String name;

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

    private Double quantity;

    private String description;

    private String specificationFileUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public void setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public void setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public PanelsDTO setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public PanelsDTO setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public PanelsDTO setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public PanelsDTO setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public PanelsDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }
}
