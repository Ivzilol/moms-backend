package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;

public class FastenerDTO extends BaseDTO {


    private String type;
    private String diameter;

    private String length;
    private LengthUnits lengthUnit;

    private String model;
    private String clazz;


    public FastenerDTO() {
    }

    public FastenerDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String diameter, String length, LengthUnits lengthUnit, String model, String clazz) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.model = model;
        this.clazz = clazz;
    }

    public FastenerDTO(String type, String diameter, String length, LengthUnits lengthUnit, String model, String clazz) {
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.model = model;
        this.clazz = clazz;
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

    public String getModel() {
        return model;
    }

    public FastenerDTO setModel(String model) {
        this.model = model;
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
