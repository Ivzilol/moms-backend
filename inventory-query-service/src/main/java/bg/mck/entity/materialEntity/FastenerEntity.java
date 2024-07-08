package bg.mck.entity.materialEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "fasteners")
public class FastenerEntity extends BaseMaterialEntity{

    private String name;
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public FastenerEntity() {
    }

    public FastenerEntity(String name, String description, String diameter, Double length, String model, String clazz, Double quantity, String note, String specificationFileUrl) {
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }


    public String getName() {
        return name;
    }

    public FastenerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FastenerEntity setDescription(String description) {
        this.description = description;
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

    public String getNote() {
        return note;
    }

    public FastenerEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastenerEntity that = (FastenerEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(diameter, that.diameter) && Objects.equals(length, that.length) && Objects.equals(model, that.model) && Objects.equals(clazz, that.clazz) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, diameter, length, model, clazz, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "FastenerEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", diameter='" + diameter + '\'' +
                ", length=" + length +
                ", model='" + model + '\'' +
                ", clazz='" + clazz + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
