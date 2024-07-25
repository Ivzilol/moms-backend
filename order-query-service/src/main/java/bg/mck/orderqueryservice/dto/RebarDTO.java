package bg.mck.orderqueryservice.dto;


import jakarta.validation.constraints.DecimalMin;

public class RebarDTO extends BaseDTO{


    private Double maxLength;

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
