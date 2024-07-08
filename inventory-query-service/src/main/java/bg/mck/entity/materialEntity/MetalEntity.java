package bg.mck.entity.materialEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "metals")
public class MetalEntity {

    private String id;

    private String name;

    @DecimalMin(value = "0.0", message = "Weight must be positive")
    @Column(name = "total_weight_in_kg")
    private Double totalWeight;

    public MetalEntity() {
    }

    public MetalEntity(String id, String name, Double totalWeight) {
        this.id = id;
        this.name = name;
        this.totalWeight = totalWeight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
