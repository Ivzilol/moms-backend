package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sets")
public class SetEntity {

    private String id;

    private String name;

    private Double galvanisedSheetThickness;

    private String color;

    private String maxLength;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public SetEntity() {
    }

    public SetEntity(String id, String name, Double galvanisedSheetThickness, String color, String maxLength, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public void setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
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
}
