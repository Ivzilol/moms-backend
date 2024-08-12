package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "sets")
public class SetEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String galvanisedSheetThickness;
    private LengthUnits galvanisedSheetThicknessUnit;
    private String color;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public SetEntity() {
    }

    // Constructor accepting Builder
    private SetEntity(Builder builder) {
        this.name = builder.name;
        this.galvanisedSheetThickness = builder.galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = builder.galvanisedSheetThicknessUnit;
        this.color = builder.color;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public SetEntity(SetEntity other) {
        this.name = other.name;
        this.galvanisedSheetThickness = other.galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = other.galvanisedSheetThicknessUnit;
        this.color = other.color;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
    public String getName() {
        return name;
    }

    public SetEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetEntity setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public SetEntity setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetEntity setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(name, setEntity.name) &&
                Objects.equals(galvanisedSheetThickness, setEntity.galvanisedSheetThickness) &&
                galvanisedSheetThicknessUnit == setEntity.galvanisedSheetThicknessUnit &&
                Objects.equals(color, setEntity.color) &&
                Objects.equals(maxLength, setEntity.maxLength) &&
                maxLengthUnit == setEntity.maxLengthUnit &&
                Objects.equals(quantity, setEntity.quantity) &&
                Objects.equals(description, setEntity.description) &&
                Objects.equals(specificationFileUrl, setEntity.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, galvanisedSheetThickness, galvanisedSheetThicknessUnit, color, maxLength, maxLengthUnit, quantity, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "name='" + name + '\'' +
                ", galvanisedSheetThickness='" + galvanisedSheetThickness + '\'' +
                ", galvanisedSheetThicknessUnit=" + galvanisedSheetThicknessUnit +
                ", color='" + color + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String galvanisedSheetThickness;
        private LengthUnits galvanisedSheetThicknessUnit;
        private String color;
        private String maxLength;
        private LengthUnits maxLengthUnit;
        private Double quantity;
        private String description;
        private String specificationFileUrl;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGalvanisedSheetThickness(String galvanisedSheetThickness) {
            this.galvanisedSheetThickness = galvanisedSheetThickness;
            return this;
        }

        public Builder setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
            this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
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

        public SetEntity build() {
            return new SetEntity(this);
        }
    }
}
