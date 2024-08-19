package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "insulation")
public class InsulationEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String type;
    private String thickness;
    private LengthUnits thicknessUnit;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public InsulationEntity() {
    }

    // Constructor accepting Builder
    private InsulationEntity(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.thickness = builder.thickness;
        this.thicknessUnit = builder.thicknessUnit;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public InsulationEntity(InsulationEntity other) {
        this.name = other.name;
        this.type = other.type;
        this.thickness = other.thickness;
        this.thicknessUnit = other.thicknessUnit;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
    public String getName() {
        return name;
    }

    public InsulationEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationEntity setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public InsulationEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsulationEntity that = (InsulationEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(thickness, that.thickness) && thicknessUnit == that.thicknessUnit && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, thickness, thicknessUnit, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "InsulationEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", thickness='" + thickness + '\'' +
                ", thicknessUnit=" + thicknessUnit +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String type;
        private String thickness;
        private LengthUnits thicknessUnit;
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

        public Builder setThickness(String thickness) {
            this.thickness = thickness;
            return this;
        }

        public Builder setThicknessUnit(LengthUnits thicknessUnit) {
            this.thicknessUnit = thicknessUnit;
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

        public InsulationEntity build() {
            return new InsulationEntity(this);
        }
    }
}
