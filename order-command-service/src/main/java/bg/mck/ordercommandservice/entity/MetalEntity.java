package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "metals")
public class MetalEntity extends BaseMaterialEntity {

    private String totalWeight;

    public MetalEntity() {
    }

    public MetalEntity(Double quantity, String note, String specificationFileUrl, String totalWeight) {
        super(quantity, note, specificationFileUrl);
        this.totalWeight = totalWeight;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalEntity setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }
}
