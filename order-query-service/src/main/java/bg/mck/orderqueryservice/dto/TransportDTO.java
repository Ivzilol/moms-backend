package bg.mck.orderqueryservice.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TransportDTO extends BaseDTO {


    @Field(name = "max_length_in_centimeters")
    private Double maxLength;

    @Field(name = "weight_in_kg")
    private Double weight;

    private String truck;

    public TransportDTO() {
    }

    public TransportDTO(Long id, Double quantity, String description, String specificationFileUrl, Double maxLength, Double weight, String truck) {
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
