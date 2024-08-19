package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "rebars")
public class RebarEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public RebarEntity() {
    }

    // Constructor accepting Builder
    private RebarEntity(Builder builder) {
        this.name = builder.name;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public RebarEntity(RebarEntity other) {
        this.name = other.name;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RebarEntity that = (RebarEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(maxLength, that.maxLength) && maxLengthUnit == that.maxLengthUnit && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxLength, maxLengthUnit, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "RebarEntity{" +
                "name='" + name + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String maxLength;
        private LengthUnits maxLengthUnit;
        private String description;
        private String specificationFileUrl;

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


        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSpecificationFileUrl(String specificationFileUrl) {
            this.specificationFileUrl = specificationFileUrl;
            return this;
        }

        public RebarEntity build() {
            return new RebarEntity(this);
        }
    }
}
