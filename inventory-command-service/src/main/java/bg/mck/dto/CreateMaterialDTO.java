package bg.mck.dto;


import bg.mck.enums.MaterialType;

public class CreateMaterialDTO {

    private MaterialType materialType;

    private String name;
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String note;
    private String specificationFileUrl;
    private String marking;
    private String number;
    private String type;
    private Double width;
    private Double area;
    private String color;
    private Double thickness;
    private Double thermalPerformance;
    private Double density;
    private Double totalThickness;
    private Double sheetThickness;
    private Integer positionNumber;
    private String steel;
    private Double weight;

    private Double maxLength;

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
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
}
