package bg.mck.entity.materialEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rebars")
public class RebarEntity {

    private String id;

    private String name;

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private Double MaxLength;

    @DecimalMin(value = "0.0", message = "weight must be positive")
    @Column(name = "weight_in_kg")
    private Double weight;

    public RebarEntity() {
    }

    public RebarEntity(String id, String name, Double maxLength, Double weight) {
        this.id = id;
        this.name = name;
        MaxLength = maxLength;
        this.weight = weight;
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

    public Double getMaxLength() {
        return MaxLength;
    }

    public void setMaxLength(Double maxLength) {
        MaxLength = maxLength;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
