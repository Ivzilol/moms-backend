package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private String type;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String numberOfSheets;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public GalvanisedSheetEntity() {
    }

    // Constructor accepting Builder
    private GalvanisedSheetEntity(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.maxLength = builder.maxLength;
        this.maxLengthUnit = builder.maxLengthUnit;
        this.numberOfSheets = builder.numberOfSheets;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public GalvanisedSheetEntity(GalvanisedSheetEntity other) {
        this.name = other.name;
        this.type = other.type;
        this.maxLength = other.maxLength;
        this.maxLengthUnit = other.maxLengthUnit;
        this.numberOfSheets = other.numberOfSheets;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
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

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetEntity setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(maxLength, that.maxLength) && maxLengthUnit == that.maxLengthUnit && Objects.equals(numberOfSheets, that.numberOfSheets) && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, maxLength, maxLengthUnit, numberOfSheets, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", maxLengthUnit=" + maxLengthUnit +
                ", numberOfSheets='" + numberOfSheets + '\'' +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String name;
        private String type;
        private String maxLength;
        private LengthUnits maxLengthUnit;
        private String numberOfSheets;
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

        public Builder setMaxLength(String maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder setMaxLengthUnit(LengthUnits maxLengthUnit) {
            this.maxLengthUnit = maxLengthUnit;
            return this;
        }

        public Builder setNumberOfSheets(String numberOfSheets) {
            this.numberOfSheets = numberOfSheets;
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

        public GalvanisedSheetEntity build() {
            return new GalvanisedSheetEntity(this);
        }
    }
}
