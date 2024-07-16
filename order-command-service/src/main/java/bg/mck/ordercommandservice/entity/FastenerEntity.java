package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "fastener")
public class FastenerEntity extends BaseMaterialEntity {
    private String type;
    private String diameter;

    @DecimalMin(value = "0.0", message = "Length must be positive")
    @Column(name = "length_in_mm")
    private Double length;

    private String model;
    private String clazz;

    public FastenerEntity() {
    }

    public FastenerEntity(Double quantity, String note, String specificationFileUrl, String type, String diameter, Double length, String model, String clazz) {
        super(quantity, note, specificationFileUrl);
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
    }

    public FastenerEntity(String type, String description, String diameter, Double length, String model, String clazz) {
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public FastenerEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerEntity setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public FastenerEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FastenerEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

}
