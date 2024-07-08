package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "insulation")
public class InsulationEntity extends BaseMaterialEntity {

    private String type;

    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    @Column(name = "thickness_in_mm")
    private Double thickness;

    public InsulationEntity() {
    }


    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }
}
