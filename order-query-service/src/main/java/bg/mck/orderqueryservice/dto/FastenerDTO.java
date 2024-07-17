package bg.mck.orderqueryservice.dto;

public class FastenerDTO extends BaseDTO {


    private String type;
    private String diameter;

    private Double length;
    private String model;
    private String clazz;


    public FastenerDTO() {
    }

    public FastenerDTO(String type, String diameter, Double length, String model, String clazz) {
        this.type = type;
        this.diameter = diameter;
        this.length = length;
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

    public String getDiameter() {
        return diameter;
    }

    public FastenerDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public FastenerDTO setLength(Double length) {
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
