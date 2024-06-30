package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marking; //Означение
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    @Column(columnDefinition = "TEXT")
    private String note;
    private String specificationFileUrl;

    @ManyToOne
    private CategoryEntity category;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Long id, String marking, String number, String type, Double length, Double width, Double area, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.marking = marking;
        this.number = number;
        this.type = type;
        this.length = length;
        this.width = width;
        this.area = area;
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

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(marking, that.marking)
                && Objects.equals(number, that.number)
                && Objects.equals(type, that.type)
                && Objects.equals(length, that.length)
                && Objects.equals(width, that.width)
                && Objects.equals(area, that.area)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(note, that.note)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marking, number, type, length, width, area, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "id=" + id +
                ", marking='" + marking + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", area=" + area +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

}
