package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

@Table
@Entity(name = "metals")
public class MetalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DecimalMin(value = "0.0", message = "Weight must be positive")
    @Column(name = "total_weight_in_kg")
    private Double totalWeight;

    @ManyToOne
    private CategoryEntity category;

    public MetalEntity() {
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

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
