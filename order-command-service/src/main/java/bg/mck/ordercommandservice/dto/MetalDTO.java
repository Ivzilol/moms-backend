package bg.mck.ordercommandservice.dto;

import jakarta.validation.constraints.DecimalMin;

public class MetalDTO extends BaseDTO{

    @DecimalMin(value = "0.0", message = "Weight must be positive")
    private String weight;

    public MetalDTO() {
    }

    public MetalDTO(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public MetalDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
