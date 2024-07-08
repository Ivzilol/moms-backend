package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseMaterialEntity {

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private Double MaxLength;

    @DecimalMin(value = "0.0", message = "weight must be positive")
    @Column(name = "weight_in_kg")
    private Double weight;

    public RebarEntity() {

    }

    public Double getMaxLength() {
        return MaxLength;
    }

    public RebarEntity setMaxLength(Double maxLength) {
        MaxLength = maxLength;
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
