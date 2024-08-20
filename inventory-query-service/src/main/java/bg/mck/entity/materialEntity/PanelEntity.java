package bg.mck.entity.materialEntity;

import bg.mck.enums.LengthUnits;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "panels")
public class PanelEntity extends BaseMaterialEntity {

    @Indexed
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
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public PanelEntity() {
    }

    // Constructor accepting Builder
    private PanelEntity(Builder builder) {
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
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public PanelEntity(PanelEntity other) {
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
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(color, that.color) && Objects.equals(length, that.length) && lengthUnit == that.lengthUnit && Objects.equals(width, that.width) && widthUnit == that.widthUnit && Objects.equals(totalThickness, that.totalThickness) && totalThicknessUnit == that.totalThicknessUnit && Objects.equals(frontSheetThickness, that.frontSheetThickness) && frontSheetThicknessUnit == that.frontSheetThicknessUnit && Objects.equals(backSheetThickness, that.backSheetThickness) && backSheetThicknessUnit == that.backSheetThicknessUnit && Objects.equals(description, that.description) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, color, length, lengthUnit, width, widthUnit, totalThickness, totalThicknessUnit, frontSheetThickness, frontSheetThicknessUnit, backSheetThickness, backSheetThicknessUnit, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "PanelEntity{" +
                "name='" + name + '\'' +
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
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
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


        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSpecificationFileUrl(String specificationFileUrl) {
            this.specificationFileUrl = specificationFileUrl;
            return this;
        }

        public PanelEntity build() {
            return new PanelEntity(this);
        }
    }
}
