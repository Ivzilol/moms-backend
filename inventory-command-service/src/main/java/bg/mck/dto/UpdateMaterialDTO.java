package bg.mck.dto;


import bg.mck.enums.AreaUnits;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;

public class UpdateMaterialDTO {
    private String name;
    private String type;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String model;
    private String clazz;
    private String description;
    private String specificationFileUrl;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String area;
    private AreaUnits areaUnit;
    private String thickness;
    private LengthUnits thicknessUnit;
    private String totalWeight;
    private WeightUnits totalWeightUnit;
    private String width;
    private LengthUnits widthUnit;
    private String totalThickness;
    private LengthUnits totalThicknessUnit;
    private String FrontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    private String BackSheetThickness;
    private LengthUnits backSheetThicknessUnit;
    private String galvanisedSheetThickness;
    private LengthUnits galvanisedSheetThicknessUnit;
    private String color;


    public String getName() {
        return name;
    }

    public UpdateMaterialDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateMaterialDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public UpdateMaterialDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public UpdateMaterialDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public UpdateMaterialDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getModel() {
        return model;
    }

    public UpdateMaterialDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UpdateMaterialDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public UpdateMaterialDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateMaterialDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public UpdateMaterialDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public UpdateMaterialDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getArea() {
        return area;
    }

    public UpdateMaterialDTO setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public UpdateMaterialDTO setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public UpdateMaterialDTO setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public UpdateMaterialDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public UpdateMaterialDTO setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public UpdateMaterialDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public UpdateMaterialDTO setWidth(String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public UpdateMaterialDTO setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public UpdateMaterialDTO setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public UpdateMaterialDTO setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public String getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public UpdateMaterialDTO setFrontSheetThickness(String frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public UpdateMaterialDTO setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public String getBackSheetThickness() {
        return BackSheetThickness;
    }

    public UpdateMaterialDTO setBackSheetThickness(String backSheetThickness) {
        BackSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public UpdateMaterialDTO setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public UpdateMaterialDTO setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public UpdateMaterialDTO setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        return this;
    }

    public String getColor() {
        return color;
    }

    public UpdateMaterialDTO setColor(String color) {
        this.color = color;
        return this;
    }
}
