package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("sets")
public class SetEntity {


    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    @Field("galvanised_sheet_thickness_in_mm")
    private Double galvanisedSheetThickness;
    private String color;
    @Field("max_length_in_centimeters")
    private String maxLength;

    public SetEntity() {
    }

    public SetEntity(String id, Double quantity, String description, String specificationFileUrl, Double galvanisedSheetThickness, String color, String maxLength) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
    }

    public String getId() {
        return id;
    }

    public SetEntity setId(String id) {
        this.id = id;
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

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetEntity setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
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
}
