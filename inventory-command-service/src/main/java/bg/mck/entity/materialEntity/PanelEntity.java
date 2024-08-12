package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.LengthUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "panels")
public class PanelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String color;
    private String length;
    @Enumerated(EnumType.STRING)
    private LengthUnits lengthUnit;
    private String width;
    @Enumerated(EnumType.STRING)
    private LengthUnits widthUnit;
    private String totalThickness;
    @Enumerated(EnumType.STRING)
    private LengthUnits totalThicknessUnit;
    private String frontSheetThickness;
    @Enumerated(EnumType.STRING)
    private LengthUnits frontSheetThicknessUnit;
    private String backSheetThickness;
    @Enumerated(EnumType.STRING)
    private LengthUnits backSheetThicknessUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    // Default constructor
    public PanelEntity() {
    }

    // Constructor accepting Builder
    private PanelEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.color = builder.color;
        this.length = builder.length;
        this.lengthUnit = builder.lengthUnit;
        this.width = builder.width;
        this.widthUnit = builder.widthUnit;
        this.totalThickness = builder.totalThickness;
        this.totalThicknessUnit = builder.totalThicknessUnit;
        this.frontSheetThickness = builder.frontSheetThickness;
        this.frontSheetThicknessUnit = builder.frontSheetThicknessUnit;
        this.backSheetThickness = builder.backSheetThickness;
        this.backSheetThicknessUnit = builder.backSheetThicknessUnit;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    // Copy constructor
    public PanelEntity(PanelEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.type = other.type;
        this.color = other.color;
        this.length = other.length;
        this.lengthUnit = other.lengthUnit;
        this.width = other.width;
        this.widthUnit = other.widthUnit;
        this.totalThickness = other.totalThickness;
        this.totalThicknessUnit = other.totalThicknessUnit;
        this.frontSheetThickness = other.frontSheetThickness;
        this.frontSheetThicknessUnit = other.frontSheetThicknessUnit;
        this.backSheetThickness = other.backSheetThickness;
        this.backSheetThicknessUnit = other.backSheetThicknessUnit;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
    }

    // Getters and setters with method chaining
    public Long getId() {
        return id;
    }

    public PanelEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PanelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public PanelEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public PanelEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public PanelEntity setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public PanelEntity setWidth(String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public PanelEntity setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public PanelEntity setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public PanelEntity setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelEntity setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public PanelEntity setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelEntity setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public PanelEntity setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PanelEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public PanelEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(type, that.type)
                && Objects.equals(color, that.color)
                && Objects.equals(length, that.length)
                && lengthUnit == that.lengthUnit
                && Objects.equals(width, that.width)
                && widthUnit == that.widthUnit
                && Objects.equals(totalThickness, that.totalThickness)
                && totalThicknessUnit == that.totalThicknessUnit
                && Objects.equals(frontSheetThickness, that.frontSheetThickness)
                && frontSheetThicknessUnit == that.frontSheetThicknessUnit
                && Objects.equals(backSheetThickness, that.backSheetThickness)
                && backSheetThicknessUnit == that.backSheetThicknessUnit
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(description, that.description)
                && Objects.equals(specificationFileUrl, that.specificationFileUrl)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, color, length, lengthUnit, width, widthUnit, totalThickness, totalThicknessUnit, frontSheetThickness, frontSheetThicknessUnit, backSheetThickness, backSheetThicknessUnit, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "PanelEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length='" + length + '\'' +
                ", lengthUnit=" + lengthUnit +
                ", width='" + width + '\'' +
                ", widthUnit=" + widthUnit +
                ", totalThickness='" + totalThickness + '\'' +
                ", totalThicknessUnit=" + totalThicknessUnit +
                ", frontSheetThickness='" + frontSheetThickness + '\'' +
                ", frontSheetThicknessUnit=" + frontSheetThicknessUnit +
                ", backSheetThickness='" + backSheetThickness + '\'' +
                ", backSheetThicknessUnit=" + backSheetThicknessUnit +
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
        private String color;
        private String length;
        private LengthUnits lengthUnit;
        private String width;
        private LengthUnits widthUnit;
        private String totalThickness;
        private LengthUnits totalThicknessUnit;
        private String frontSheetThickness;
        private LengthUnits frontSheetThicknessUnit;
        private String backSheetThickness;
        private LengthUnits backSheetThicknessUnit;
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

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setLength(String length) {
            this.length = length;
            return this;
        }

        public Builder setLengthUnit(LengthUnits lengthUnit) {
            this.lengthUnit = lengthUnit;
            return this;
        }

        public Builder setWidth(String width) {
            this.width = width;
            return this;
        }

        public Builder setWidthUnit(LengthUnits widthUnit) {
            this.widthUnit = widthUnit;
            return this;
        }

        public Builder setTotalThickness(String totalThickness) {
            this.totalThickness = totalThickness;
            return this;
        }

        public Builder setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
            this.totalThicknessUnit = totalThicknessUnit;
            return this;
        }

        public Builder setFrontSheetThickness(String frontSheetThickness) {
            this.frontSheetThickness = frontSheetThickness;
            return this;
        }

        public Builder setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
            this.frontSheetThicknessUnit = frontSheetThicknessUnit;
            return this;
        }

        public Builder setBackSheetThickness(String backSheetThickness) {
            this.backSheetThickness = backSheetThickness;
            return this;
        }

        public Builder setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
            this.backSheetThicknessUnit = backSheetThicknessUnit;
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

        public PanelEntity build() {
            return new PanelEntity(this);
        }
    }
}
