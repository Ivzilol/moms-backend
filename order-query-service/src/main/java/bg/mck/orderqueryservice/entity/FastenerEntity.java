package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("fastener")
public class FastenerEntity {

    private String id;
    private String type;
    private String diameter;

    @Field("length_in_mm")
    private Double length;
    private String model;
    private String clazz;

    private Double quantity;
    private String description;
    private String specificationFileUrl;


    public FastenerEntity() {
    }


    public FastenerEntity(String id, String type, String diameter, Double length, String model, String clazz, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public FastenerEntity setId(String id) {
        this.id = id;
        return this;
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

    public Double getQuantity() {
        return quantity;
    }

    public FastenerEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FastenerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
