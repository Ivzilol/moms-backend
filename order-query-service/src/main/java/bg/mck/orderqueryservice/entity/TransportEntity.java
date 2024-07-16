package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("transports")
public class TransportEntity {
    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    @Field("max_length_in_centimeters")
    private Double maxLength;
    @Field("weight_in_kg")
    private Double weight;
    private String Truck;


    public TransportEntity() {
    }

    public TransportEntity(String id, Double quantity, String description, String specificationFileUrl, Double maxLength, Double weight, String truck) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.maxLength = maxLength;
        this.weight = weight;
        Truck = truck;
    }

    public String getId() {
        return id;
    }

    public TransportEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public TransportEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public TransportEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public TransportEntity setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public TransportEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return Truck;
    }

    public TransportEntity setTruck(String truck) {
        Truck = truck;
        return this;
    }
}
