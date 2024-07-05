package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "transports")
public class TransportEntity extends BaseMaterialEntity {

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private Double maxLength;

    @DecimalMin(value = "0.0", message = "weight must be positive")
    @Column(name = "weight_in_kg")
    private Double weight;

    private String Truck;


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

    public TransportEntity() {


    }
}
