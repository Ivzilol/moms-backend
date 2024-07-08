package bg.mck.entity.transportEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transports")
public class TransportEntity {

    private String id;

    private String name;

    private Double maxLength;

    private Double weight;

    private String Truck;

    public TransportEntity() {
    }

    public TransportEntity(String id, String name, Double maxLength, Double weight, String truck) {
        this.id = id;
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        Truck = truck;
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
}
