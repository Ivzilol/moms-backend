package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.WeightUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "metals")
public class MetalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String totalWeight;

    @Enumerated(EnumType.STRING)
    private WeightUnits totalWeightUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    // Default constructor
    public MetalEntity() {
    }

    // Constructor accepting Builder
    private MetalEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.totalWeight = builder.totalWeight;
        this.totalWeightUnit = builder.totalWeightUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    // Copy constructor
    public MetalEntity(MetalEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.totalWeight = other.totalWeight;
        this.totalWeightUnit = other.totalWeightUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
    }

    // Getters and setters with method chaining
    public Long getId() {
        return id;
    }

    public MetalEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MetalEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalEntity setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalEntity setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public MetalEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetalEntity that = (MetalEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(totalWeight, that.totalWeight)
                && totalWeightUnit == that.totalWeightUnit
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(description, that.description)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalWeight, totalWeightUnit, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "MetalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                ", totalWeightUnit=" + totalWeightUnit +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                ", category=" + category +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private Long id;
        private String name;
        private String totalWeight;
        private WeightUnits totalWeightUnit;
        private Double quantity;
        private String description;
        private String specificationFileUrl;
        private CategoryEntity category;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTotalWeight(String totalWeight) {
            this.totalWeight = totalWeight;
            return this;
        }

        public Builder setTotalWeightUnit(WeightUnits totalWeightUnit) {
            this.totalWeightUnit = totalWeightUnit;
            return this;
        }

        public Builder setQuantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSpecificationFileUrl(String specificationFileUrl) {
            this.specificationFileUrl = specificationFileUrl;
            return this;
        }

        public Builder setCategory(CategoryEntity category) {
            this.category = category;
            return this;
        }

        public MetalEntity build() {
            return new MetalEntity(this);
        }
    }
}
