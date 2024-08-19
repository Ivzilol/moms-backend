package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "fastener")
public class FastenerEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String type;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public FastenerEntity() {
    }

    // Constructor accepting Builder
    private FastenerEntity(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.diameter = builder.diameter;
        this.length = builder.length;
        this.lengthUnit = builder.lengthUnit;
        this.standard = builder.standard;
        this.clazz = builder.clazz;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public FastenerEntity(FastenerEntity other) {
        this.name = other.name;
        this.type = other.type;
        this.diameter = other.diameter;
        this.length = other.length;
        this.lengthUnit = other.lengthUnit;
        this.standard = other.standard;
        this.clazz = other.clazz;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
    public String getName() {
        return name;
    }

    public FastenerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public FastenerEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerEntity setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FastenerEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerEntity setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerEntity setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public FastenerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastenerEntity that = (FastenerEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(diameter, that.diameter) && Objects.equals(length, that.length) && lengthUnit == that.lengthUnit && Objects.equals(standard, that.standard) && Objects.equals(clazz, that.clazz) && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, diameter, length, lengthUnit, standard, clazz, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "FastenerEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", diameter='" + diameter + '\'' +
                ", length='" + length + '\'' +
                ", lengthUnit=" + lengthUnit +
                ", standard='" + standard + '\'' +
                ", clazz='" + clazz + '\'' +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String type;
        private String diameter;
        private String length;
        private LengthUnits lengthUnit;
        private String standard;
        private String clazz;
        private String description;
        private String specificationFileUrl;

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

        public Builder setStandard(String standard) {
            this.standard = standard;
            return this;
        }

        public Builder setClazz(String clazz) {
            this.clazz = clazz;
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

        public FastenerEntity build() {
            return new FastenerEntity(this);
        }
    }
}
