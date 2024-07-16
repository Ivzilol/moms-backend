package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("services")
public class ServiceEntity {
    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;


    public ServiceEntity() {
    }


    public ServiceEntity(String id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public ServiceEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ServiceEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public ServiceEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
