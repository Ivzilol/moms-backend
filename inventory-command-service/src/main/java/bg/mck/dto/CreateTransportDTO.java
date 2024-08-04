package bg.mck.dto;

import jakarta.validation.constraints.DecimalMin;

public class CreateTransportDTO {

    private String name;
    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    private Double maxLength;
    @DecimalMin(value = "0.0", message = "weight must be positive")
    private Double weight;
    private String Truck;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public String getName() {
        return name;
    }

    public CreateTransportDTO setName(String name) {
        this.name = name;
        return this;
    }

    public @DecimalMin(value = "0.0", message = "MaxLength must be positive") Double getMaxLength() {
        return maxLength;
    }

    public CreateTransportDTO setMaxLength(@DecimalMin(value = "0.0", message = "MaxLength must be positive") Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public @DecimalMin(value = "0.0", message = "weight must be positive") Double getWeight() {
        return weight;
    }

    public CreateTransportDTO setWeight(@DecimalMin(value = "0.0", message = "weight must be positive") Double weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return Truck;
    }

    public CreateTransportDTO setTruck(String truck) {
        Truck = truck;
        return this;
    }

    public @DecimalMin(value = "0.0", message = "Quantity must be positive") Double getQuantity() {
        return quantity;
    }

    public CreateTransportDTO setQuantity(@DecimalMin(value = "0.0", message = "Quantity must be positive") Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateTransportDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public CreateTransportDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
