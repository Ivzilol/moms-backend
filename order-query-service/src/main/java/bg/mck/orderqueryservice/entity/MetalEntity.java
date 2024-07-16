package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("metals")
public class MetalEntity {

    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    @Field("total_weight_in_kg")
    private Double totalWeight;

    public MetalEntity() {
    }


    public MetalEntity(String id, Double quantity, String description, String specificationFileUrl, Double totalWeight) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.totalWeight = totalWeight;
    }

    public String getId() {
        return id;
    }

    public MetalEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public MetalEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }
}
