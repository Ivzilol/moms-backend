package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "panels")
public class PanelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double sheetThickness;
    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    @ManyToOne
    private CategoryEntity category;

    public PanelEntity() {
    }

    public PanelEntity(Long id, String type, String color, Double length, Double width, Double totalThickness, Double sheetThickness, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        this.sheetThickness = sheetThickness;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Double getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
    }

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public void setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
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
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(type, that.type)
                && Objects.equals(color, that.color)
                && Objects.equals(length, that.length)
                && Objects.equals(width, that.width)
                && Objects.equals(totalThickness, that.totalThickness)
                && Objects.equals(sheetThickness, that.sheetThickness)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(note, that.note)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, color, length, width, totalThickness, sheetThickness, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "PanelEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", totalThickness=" + totalThickness +
                ", sheetThickness=" + sheetThickness +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
