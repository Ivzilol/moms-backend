package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rebars")
public class RebarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    private CategoryEntity category;

    public RebarEntity() {
    }

    public RebarEntity(Long id, Integer positionNumber, String type, String steel, Double diameter, Double length, Double weight, Double quantity, String note, String specificationFileUrl) {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSteel() {
        return steel;
    }

    public void setSteel(String steel) {
        this.steel = steel;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RebarEntity that = (RebarEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(positionNumber, that.positionNumber)
                && Objects.equals(type, that.type)
                && Objects.equals(steel, that.steel)
                && Objects.equals(diameter, that.diameter)
                && Objects.equals(length, that.length)
                && Objects.equals(weight, that.weight)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(note, that.note)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionNumber, type, steel, diameter, length, weight, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "RebarRepository{" +
                "id=" + id +
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
