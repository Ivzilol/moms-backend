package bg.mck.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

public class MetalUpdateDTO {

    private String name;
    @DecimalMin(value = "0.0", message = "Weight must be positive")
    private Double totalWeight;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public MetalUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public MetalUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public MetalUpdateDTO setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
