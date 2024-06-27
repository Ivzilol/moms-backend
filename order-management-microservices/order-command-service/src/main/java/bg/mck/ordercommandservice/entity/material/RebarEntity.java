package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//армировка
@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseEntity {

    private Integer positionNumber;
    private String type;
    private String steel;
    private Double diameter;
    private Double length;
    private Double weight;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public RebarEntity() {
    }

    public RebarEntity(Integer positionNumber, String type, String steel, Double diameter, Double length, Double weight, Double quantity, String note, String specificationFileUrl) {
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
}
