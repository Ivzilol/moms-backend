package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;

public class FastenerDTO extends BaseDTO {


    private String type;
    private String diameter;

    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;


    public FastenerDTO() {
    }

    public FastenerDTO(String id, String standard, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String diameter, String length, LengthUnits lengthUnit, String clazz) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.diameter = diameter;
        this.standard = standard;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.clazz = clazz;
    }

    public FastenerDTO(String type, String diameter, String length, LengthUnits lengthUnit, String standard, String clazz) {
        this.type = type;
        this.diameter = diameter;
        this.standard = standard;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.clazz = clazz;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerDTO setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getType() {
        return type;
    }

    public FastenerDTO setType(String type) {
        this.type = type;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerDTO setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
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

    public String getClazz() {
        return clazz;
    }

    public FastenerDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


}
