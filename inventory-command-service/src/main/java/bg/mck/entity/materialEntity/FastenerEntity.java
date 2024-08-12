package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.LengthUnits;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "fastener")
public class FastenerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //type + diameter + length
    private String name;

    private String type;

    private String diameter;

    private String length;
    @Enumerated(EnumType.STRING)
    private LengthUnits lengthUnit;

    private String model;

    private String clazz;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @ManyToOne
    private CategoryEntity category;

    public FastenerEntity() {
    }

    private FastenerEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.diameter = builder.diameter;
        this.length = builder.length;
        this.lengthUnit = builder.lengthUnit;
        this.model = builder.model;
        this.clazz = builder.clazz;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
        this.category = builder.category;
    }

    public FastenerEntity(FastenerEntity other) {
        this.id = other.id;
        this.name = other.name;
        this.type = other.type;
        this.diameter = other.diameter;
        this.length = other.length;
        this.lengthUnit = other.lengthUnit;
        this.model = other.model;
        this.clazz = other.clazz;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
        this.category = other.category;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerEntity setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastenerEntity that = (FastenerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(diameter, that.diameter) && Objects.equals(length, that.length) && lengthUnit == that.lengthUnit && Objects.equals(model, that.model) && Objects.equals(clazz, that.clazz) && Objects.equals(quantity, that.quantity) && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, diameter, length, lengthUnit, model, clazz, quantity, description, specificationFileUrl, category);
    }

    @Override
    public String toString() {
        return "FastenerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", diameter='" + diameter + '\'' +
                ", length='" + length + '\'' +
                ", lengthUnit=" + lengthUnit +
                ", model='" + model + '\'' +
                ", clazz='" + clazz + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                ", category=" + category +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String type;
        private String diameter;
        private String length;
        private LengthUnits lengthUnit;
        private String model;
        private String clazz;
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

        public Builder setDiameter(String diameter) {
            this.diameter = diameter;
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

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setClazz(String clazz) {
            this.clazz = clazz;
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

        public FastenerEntity build() {
            return new FastenerEntity(this);
        }
    }
}
