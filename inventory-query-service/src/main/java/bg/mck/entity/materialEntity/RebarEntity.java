package bg.mck.entity.materialEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "rebars")
public class RebarEntity {

    @Id
    private String id;
    private Integer positionNumber;
    private String type;
    private String steel;
    private Double diameter;
    private Double length;
    private Double weight;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public RebarEntity() {
    }

    public RebarEntity(String id, Integer positionNumber, String type, String steel, Double diameter, Double length, Double weight, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.positionNumber = positionNumber;
        this.type = type;
        this.steel = steel;
        this.diameter = diameter;
        this.length = length;
        this.weight = weight;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public RebarEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public RebarEntity setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    public String getType() {
        return type;
    }

    public RebarEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getSteel() {
        return steel;
    }

    public RebarEntity setSteel(String steel) {
        this.steel = steel;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public RebarEntity setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public RebarEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RebarEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RebarEntity that = (RebarEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(positionNumber, that.positionNumber) && Objects.equals(type, that.type) && Objects.equals(steel, that.steel) && Objects.equals(diameter, that.diameter) && Objects.equals(length, that.length) && Objects.equals(weight, that.weight) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionNumber, type, steel, diameter, length, weight, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "RebarEntity{" +
                "id='" + id + '\'' +
                ", positionNumber=" + positionNumber +
                ", type='" + type + '\'' +
                ", steel='" + steel + '\'' +
                ", diameter=" + diameter +
                ", length=" + length +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
