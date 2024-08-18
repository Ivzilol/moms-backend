package bg.mck.dto;


import bg.mck.enums.AreaUnits;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static bg.mck.errors.ErrorsCreateMaterial.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMaterialDTO {

    private MaterialType materialType;
    private String name;
    private String description;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String note;
    private String specificationFileUrl;
    private String marking;
    private String number;
    private String type;
    private String kind;
    private String width;
    private LengthUnits widthUnit;
    private String numberOfSheets;
    private String color;
    private String thickness;
    private LengthUnits thicknessUnit;
    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;
    private Double thermalPerformance;
    private Double density;
    private String totalThickness;
    private LengthUnits totalThicknessUnit;
    private Double sheetThickness;
    private Integer positionNumber;
    private String steel;
    private String weight;
    private WeightUnits weightUnit;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String totalWeight;
    private WeightUnits totalWeightUnit;
    private String galvanisedSheetThickness;
    private LengthUnits galvanisedSheetThicknessUnit;


    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public void setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public void setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public Double getThermalPerformance() {
        return thermalPerformance;
    }

    public void setThermalPerformance(Double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
    }

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public void setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getSteel() {
        return steel;
    }

    public void setSteel(String steel) {
        this.steel = steel;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
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


    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public CreateMaterialDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public CreateMaterialDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }


    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public CreateMaterialDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public CreateMaterialDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public CreateMaterialDTO setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public CreateMaterialDTO setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public CreateMaterialDTO setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public CreateMaterialDTO setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public CreateMaterialDTO setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public CreateMaterialDTO setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public CreateMaterialDTO setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
