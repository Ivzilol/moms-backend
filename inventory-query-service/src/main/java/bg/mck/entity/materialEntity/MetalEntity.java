package bg.mck.entity.materialEntity;

import bg.mck.enums.WeightUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "metals")
public class MetalEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String totalWeight;
    private WeightUnits totalWeightUnit;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public MetalEntity() {
    }

    // Constructor accepting Builder
    private MetalEntity(Builder builder) {
        this.name = builder.name;
        this.totalWeight = builder.totalWeight;
        this.totalWeightUnit = builder.totalWeightUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public MetalEntity(MetalEntity other) {
        this.name = other.name;
        this.totalWeight = other.totalWeight;
        this.totalWeightUnit = other.totalWeightUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetalEntity that = (MetalEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(totalWeight, that.totalWeight) &&
                totalWeightUnit == that.totalWeightUnit &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(description, that.description) &&
                Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalWeight, totalWeightUnit, quantity, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "MetalEntity{" +
                "name='" + name + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                ", totalWeightUnit=" + totalWeightUnit +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String totalWeight;
        private WeightUnits totalWeightUnit;
        private Double quantity;
        private String description;
        private String specificationFileUrl;

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

        public MetalEntity build() {
            return new MetalEntity(this);
        }
    }
}
