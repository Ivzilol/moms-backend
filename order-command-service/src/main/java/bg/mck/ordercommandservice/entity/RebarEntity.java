package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseMaterialEntity {

    private String maxLength;
    private String weight;

    public RebarEntity() {

    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
