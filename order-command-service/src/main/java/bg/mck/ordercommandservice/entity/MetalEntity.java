package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "metals")
public class MetalEntity extends BaseMaterialEntity {

    @DecimalMin(value = "0.0", message = "Weight must be positive")
    @Column(name = "total_weight_in_kg")
    private Double totalWeight;


    public MetalEntity() {
    }

    public MetalEntity(Double quantity, String note, String specificationFileUrl, Double totalWeight) {
        super(quantity, note, specificationFileUrl);
        this.totalWeight = totalWeight;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public MetalEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }
}
