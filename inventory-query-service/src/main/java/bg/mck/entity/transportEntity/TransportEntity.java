package bg.mck.entity.transportEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transports")
public class TransportEntity {

    private String id;
    private String name;
    private Double maxLength;
    private Double weight;
    private String Truck;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public TransportEntity() {
    }

    public TransportEntity(String id, String name, Double maxLength, Double weight, String truck, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        Truck = truck;
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

    public Double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getTruck() {
        return Truck;
    }

    public void setTruck(String truck) {
        Truck = truck;
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
