package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Ламарина
@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity  extends BaseEntity {

    private Double quantity;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
