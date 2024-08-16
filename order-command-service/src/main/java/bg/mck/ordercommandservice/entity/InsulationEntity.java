package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "insulation")
public class InsulationEntity extends BaseMaterialEntity {

    @Column(nullable = false)
    private String type;
    private String thickness;
    @Column(nullable = false)
    private String quantity;


    public InsulationEntity() {
    }


    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public InsulationEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
