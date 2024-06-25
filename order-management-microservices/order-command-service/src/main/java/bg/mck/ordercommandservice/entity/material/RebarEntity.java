package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//армировка
@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseEntity {
    private Double quantity;

    public RebarEntity() {
    }

    public RebarEntity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
