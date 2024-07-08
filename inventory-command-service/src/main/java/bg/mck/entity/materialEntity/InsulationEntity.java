package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "insulation")
public class InsulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    @Column(name = "thickness_in_mm")
    private Double thickness;

    @ManyToOne
    private CategoryEntity category;

    public InsulationEntity() {
    }

    public InsulationEntity(Long id, String name, Double thickness, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.thickness = thickness;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
