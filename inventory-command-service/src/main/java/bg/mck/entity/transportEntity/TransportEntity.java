package bg.mck.entity.transportEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "transports")
public class TransportEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private Double maxLength;

    @DecimalMin(value = "0.0", message = "weight must be positive")
    @Column(name = "weight_in_kg")
    private Double weight;

    private String Truck;

    public TransportEntity() {
    }

    public TransportEntity(Long id, String name, Double maxLength, Double weight, String truck) {
        this.id = id;
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        Truck = truck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
