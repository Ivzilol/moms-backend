package bg.mck.ordercommandservice.dto;


import jakarta.validation.constraints.DecimalMin;

public class RebarDTO extends BaseDTO{

    @DecimalMin(value = "0.0", message = "Max length must be positive")
    private Double maxLength;
    @DecimalMin(value = "0.0", message = "Weight must be positive")
    private Double weight;

    public RebarDTO() {
    }

    public RebarDTO(Double maxLength, Double weight) {
        this.maxLength = maxLength;
        this.weight = weight;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public RebarDTO setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }
}
