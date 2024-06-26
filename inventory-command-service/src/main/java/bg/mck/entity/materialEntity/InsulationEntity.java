package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "insulation")
public class InsulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String color;
    private String length;
    private String width;
    private Double thickness;
    private Double thermalPerformance;
    private Double density;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    @ManyToOne
    private CategoryEntity category;

    public InsulationEntity() {
    }

    public InsulationEntity(Long id, String type, String color, String length, String width, Double thickness, Double thermalPerformance, Double density, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.thermalPerformance = thermalPerformance;
        this.density = density;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getThermalPerformance() {
        return thermalPerformance;
    }

    public void setThermalPerformance(Double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
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
        InsulationEntity that = (InsulationEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(type, that.type)
                && Objects.equals(color, that.color)
                && Objects.equals(length, that.length)
                && Objects.equals(width, that.width)
                && Objects.equals(thickness, that.thickness)
                && Objects.equals(thermalPerformance, that.thermalPerformance)
                && Objects.equals(density, that.density)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(note, that.note)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, color, length, width, thickness, thermalPerformance, density, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "InsulationEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", thickness=" + thickness +
                ", thermalPerformance=" + thermalPerformance +
                ", density=" + density +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
