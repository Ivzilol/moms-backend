package bg.mck.orderqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fastener")
public class FastenerEntity extends BaseMaterialEntity {
    private String type;
    private String diameter;
    private String length;
    private String model;
    private String clazz;

    public FastenerEntity() {
    }

    public FastenerEntity(Double quantity, String note, String specificationFileUrl, String type, String diameter, String length, String model, String clazz) {
        super(quantity, note, specificationFileUrl);
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
    }

    public FastenerEntity(String type, String description, String diameter, String length, String model, String clazz) {
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

    public String getLength() {
        return length;
    }

    public FastenerEntity setLength(String length) {
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
