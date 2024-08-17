package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("fastener")
public class FastenerEntity extends BaseMaterialEntity {
    private String type;
    private String diameter;
    private String length;
    private String standard;
    private String clazz;
    private String quantity;

    public FastenerEntity() {
    }

    public FastenerEntity(String quantity, String note, String specificationFileUrl, String type, String diameter, String length, String standard, String clazz) {
        super(note, specificationFileUrl);
        this.quantity = quantity;
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.standard = standard;
        this.clazz = clazz;
    }

    public FastenerEntity(String type, String description, String diameter, String length, String standard, String clazz) {
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.standard = standard;
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

    public String getStandard() {
        return standard;
    }

    public FastenerEntity setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public FastenerEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
