package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("rebars")
public class RebarEntity {

    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    @Field("max_length_in_centimeters")
    private Double maxLength;
    @Field("weight_in_kg")
    private Double weight;


    public RebarEntity() {
    }

    public RebarEntity(String id, Double quantity, String description, String specificationFileUrl, Double maxLength, Double weight) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.maxLength = maxLength;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public RebarEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RebarEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }
}
