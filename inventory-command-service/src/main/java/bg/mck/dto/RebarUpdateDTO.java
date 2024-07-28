package bg.mck.dto;

import jakarta.validation.constraints.DecimalMin;

public class RebarUpdateDTO {

    private String name;
    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    private Double MaxLength;
    @DecimalMin(value = "0.0", message = "weight must be positive")
    private Double weight;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;


    private String specificationFileUrl;

    public RebarUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public RebarUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getMaxLength() {
        return MaxLength;
    }

    public RebarUpdateDTO setMaxLength(Double maxLength) {
        MaxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarUpdateDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RebarUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
