package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.AreaUnits;
import bg.mck.enums.LengthUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Column(name = "max_length")
    private String maxLength;

    @Enumerated(EnumType.STRING)
    private LengthUnits maxLengthUnit;

    private String area;

    @Enumerated(EnumType.STRING)
    private AreaUnits areaUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    // Default constructor
    public GalvanisedSheetEntity() {
    }

    // Constructor accepting Builder
    private GalvanisedSheetEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.area = builder.area;
        this.areaUnit = builder.areaUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    // Copy constructor
    public GalvanisedSheetEntity(GalvanisedSheetEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.type = other.type;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.area = other.area;
        this.areaUnit = other.areaUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
    }

    // Getters and setters with method chaining
    public Long getId() {
        return id;
    }

    public GalvanisedSheetEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GalvanisedSheetEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanisedSheetEntity setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public GalvanisedSheetEntity setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalvanisedSheetEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanisedSheetEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public GalvanisedSheetEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(type, that.type)
                && Objects.equals(maxLength, that.maxLength)
                && maxLengthUnit == that.maxLengthUnit
                && Objects.equals(area, that.area)
                && areaUnit == that.areaUnit
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(description, that.description)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, maxLength, maxLengthUnit, area, areaUnit, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
                ", area='" + area + '\'' +
                ", areaUnit=" + areaUnit +
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
        private String type;
        private String maxLength;
        private LengthUnits maxLengthUnit;
        private String area;
        private AreaUnits areaUnit;
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

        public Builder setType(String type) {
            this.type = type;
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

        public Builder setArea(String area) {
            this.area = area;
            return this;
        }

        public Builder setAreaUnit(AreaUnits areaUnit) {
            this.areaUnit = areaUnit;
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

        public GalvanisedSheetEntity build() {
            return new GalvanisedSheetEntity(this);
        }
    }
}
