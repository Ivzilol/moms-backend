package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FastenerEntity that = (FastenerEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(diameter, that.diameter) &&
                Objects.equals(length, that.length) &&
                Objects.equals(standard, that.standard) &&
                Objects.equals(clazz, that.clazz) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, diameter, length, standard, clazz, quantity);
    }

    @Override
    public String toString() {
        return "FastenerEntity{" +
                "type='" + type + '\'' +
                ", diameter='" + diameter + '\'' +
                ", length='" + length + '\'' +
                ", standard='" + standard + '\'' +
                ", clazz='" + clazz + '\'' +
                ", quantity='" + quantity + '\'' +
                ", note='" + getAdminNote() + '\'' +
                ", specificationFileUrl='" + getSpecificationFileUrl() + '\'' +
                '}';
    }
}
