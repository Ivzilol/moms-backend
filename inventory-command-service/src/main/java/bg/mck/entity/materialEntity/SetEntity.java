package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.LengthUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "sets")
public class SetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String galvanisedSheetThickness;

    @Enumerated(EnumType.STRING)
    private LengthUnits galvanisedSheetThicknessUnit;

    private String color;

    private String maxLength;

    @Enumerated(EnumType.STRING)
    private LengthUnits maxLengthUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    // Default constructor
    public SetEntity() {
    }

    // Constructor accepting Builder
    private SetEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.galvanisedSheetThickness = builder.galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = builder.galvanisedSheetThicknessUnit;
        this.color = builder.color;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    // Copy constructor
    public SetEntity(SetEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.galvanisedSheetThickness = other.galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = other.galvanisedSheetThicknessUnit;
        this.color = other.color;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
    }

    // Getters and setters with method chaining
    public Long getId() {
        return id;
    }

    public SetEntity setId(Long id) {
        this.id = id;
        return this;
    }

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

    public CategoryEntity getCategory() {
        return category;
    }

    public SetEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(id, setEntity.id)
                && Objects.equals(name, setEntity.name)
                && Objects.equals(galvanisedSheetThickness, setEntity.galvanisedSheetThickness)
                && galvanisedSheetThicknessUnit == setEntity.galvanisedSheetThicknessUnit
                && Objects.equals(color, setEntity.color)
                && Objects.equals(maxLength, setEntity.maxLength)
                && maxLengthUnit == setEntity.maxLengthUnit
                && Objects.equals(quantity, setEntity.quantity)
                && Objects.equals(description, setEntity.description)
                && Objects.equals(specificationFileUrl, setEntity.specificationFileUrl)
                && Objects.equals(category, setEntity.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, galvanisedSheetThickness, galvanisedSheetThicknessUnit, color, maxLength, maxLengthUnit, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", galvanisedSheetThickness='" + galvanisedSheetThickness + '\'' +
                ", galvanisedSheetThicknessUnit=" + galvanisedSheetThicknessUnit +
                ", color='" + color + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
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
        private String galvanisedSheetThickness;
        private LengthUnits galvanisedSheetThicknessUnit;
        private String color;
        private String maxLength;
        private LengthUnits maxLengthUnit;
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

        public Builder setCategory(CategoryEntity category) {
            this.category = category;
            return this;
        }

        public SetEntity build() {
            return new SetEntity(this);
        }
    }
}
