package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "rebars")
public class RebarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String maxLength;

    @Enumerated(EnumType.STRING)
    private LengthUnits maxLengthUnit;

    private String weight;

    @Enumerated(EnumType.STRING)
    private WeightUnits weightUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    // Default constructor
    public RebarEntity() {
    }

    // Constructor accepting Builder
    private RebarEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.weight = builder.weight;
        this.weightUnit = builder.weightUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    // Copy constructor
    public RebarEntity(RebarEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.weight = other.weight;
        this.weightUnit = other.weightUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
    }

    // Getters and setters with method chaining
    public Long getId() {
        return id;
    }

    public RebarEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RebarEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarEntity setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public RebarEntity setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RebarEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public RebarEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RebarEntity that = (RebarEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(maxLength, that.maxLength)
                && maxLengthUnit == that.maxLengthUnit
                && Objects.equals(weight, that.weight)
                && weightUnit == that.weightUnit
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(description, that.description)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxLength, maxLengthUnit, weight, weightUnit, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "RebarEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
                ", weight='" + weight + '\'' +
                ", weightUnit=" + weightUnit +
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
        private String maxLength;
        private LengthUnits maxLengthUnit;
        private String weight;
        private WeightUnits weightUnit;
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

        public Builder setMaxLength(String maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder setMaxLengthUnit(LengthUnits maxLengthUnit) {
            this.maxLengthUnit = maxLengthUnit;
            return this;
        }

        public Builder setWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder setWeightUnit(WeightUnits weightUnit) {
            this.weightUnit = weightUnit;
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

        public RebarEntity build() {
            return new RebarEntity(this);
        }
    }
}
