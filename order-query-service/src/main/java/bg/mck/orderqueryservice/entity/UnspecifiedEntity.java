package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("unspecified")
public class UnspecifiedEntity {
    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public UnspecifiedEntity() {
    }


    public UnspecifiedEntity(String id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public UnspecifiedEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
