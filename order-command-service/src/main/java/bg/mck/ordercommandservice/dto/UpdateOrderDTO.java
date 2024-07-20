package bg.mck.ordercommandservice.dto;

import jakarta.validation.constraints.DecimalMin;

import static bg.mck.ordercommandservice.errors.ErrorsUpdateMaterial.*;

public class UpdateOrderDTO {

    private String id;
    private String materialType;
    private Long orderNumber;
    private String name;
    private String description;
    private String diameter;
    @DecimalMin(value = "0.0", message = INVALID_LENGTH)
    private Double length;
    private String model;
    private String clazz;
    @DecimalMin(value = "0.0", message = INVALID_QUANTITY)
    private Double quantity;
    private String note;
    private String specificationFileUrl;
    private String marking;
    private String number;
    private String type;
    private Double width;
    @DecimalMin(value = "0.0", inclusive = false, message = INVALID_AREA)
    private Double area;
    private String color;
    @DecimalMin(value = "0.0", message = INVALID_THICKNESS)
    private Double thickness;

    @DecimalMin(value = "0.0", message = INVALID_FRONT_THICKNESS)
    private Double frontSheetThickness;

    @DecimalMin(value = "0.0", message = INVALID_BACK_THICKNESS)
    private Double backSheetThickness;
    private Double thermalPerformance;
    private Double density;
    @DecimalMin(value = "0.0", message = INVALID_THICKNESS)
    private Double totalThickness;
    private Double sheetThickness;
    private Integer positionNumber;
    private String steel;
    @DecimalMin(value = "0.0", message = INVALID_WIDTH)
    private Double weight;
    @DecimalMin(value = "0.0", inclusive = false, message = INVALID_MAX_LENGTH)
    private Double maxLength;

    @DecimalMin(value = "0.0", message = INVALID_TOTAL_WEIGHT)
    private Double totalWeight;

    @DecimalMin(value = "0.0", message = INVALID_GALVANISE)
    private Double galvanisedSheetThickness;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public void setFrontSheetThickness(Double frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
    }

    public Double getBackSheetThickness() {
        return backSheetThickness;
    }

    public void setBackSheetThickness(Double backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
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

    public Double getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(Double totalThickness) {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public void setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
    }
}
