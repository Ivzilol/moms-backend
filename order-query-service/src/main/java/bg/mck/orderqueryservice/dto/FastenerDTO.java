package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;

public class FastenerDTO extends BaseDTO {


    private String type;
    private String diameter;

    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String quantity;


    public FastenerDTO() {
    }

    public FastenerDTO(String type, String diameter, String length, LengthUnits lengthUnit, String standard, String clazz, String quantity) {
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.standard = standard;
        this.clazz = clazz;
        this.quantity = quantity;
    }

    public FastenerDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String diameter, String length, LengthUnits lengthUnit, String standard, String clazz, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.standard = standard;
        this.clazz = clazz;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public FastenerDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FastenerDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerDTO setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public FastenerDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
