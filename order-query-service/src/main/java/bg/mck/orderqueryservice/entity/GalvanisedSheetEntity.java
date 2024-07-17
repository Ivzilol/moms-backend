package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("galvanised_sheets")
public class GalvanisedSheetEntity {

    private String id;
    private String type;
    private Double maxLength;
    private Double area;

    private Double quantity;
    private String description;
    private String specificationFileUrl;


    public GalvanisedSheetEntity() {
    }


    public GalvanisedSheetEntity(String id, String type, Double maxLength, Double area, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public GalvanisedSheetEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(Double area) {
        this.area = area;
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
}
