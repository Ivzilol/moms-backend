package bg.mck.orderqueryservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class TransportDTO extends BaseDTO {


    private Double maxLength;

    private Double weight;

    private String truck;

    public TransportDTO() {
    }

    public TransportDTO(String id, Double quantity, String description, String specificationFileUrl, Double maxLength, Double weight, String truck) {
        super(id, quantity, description, specificationFileUrl);
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public TransportDTO setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public TransportDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportDTO setTruck(String truck) {
        this.truck = truck;
        return this;
    }
}
