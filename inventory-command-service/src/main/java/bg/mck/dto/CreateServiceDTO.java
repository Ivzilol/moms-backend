package bg.mck.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class CreateServiceDTO {

    private String name;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String specificationFileUrl;


    public String getName() {
        return name;
    }

    public CreateServiceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public @DecimalMin(value = "0.0", message = "Quantity must be positive") Double getQuantity() {
        return quantity;
    }

    public CreateServiceDTO setQuantity(@DecimalMin(value = "0.0", message = "Quantity must be positive") Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateServiceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public CreateServiceDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
