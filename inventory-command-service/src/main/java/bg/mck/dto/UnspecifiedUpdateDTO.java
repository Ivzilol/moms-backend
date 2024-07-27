package bg.mck.dto;


import jakarta.validation.constraints.DecimalMin;

public class UnspecifiedUpdateDTO {

    private String name;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;

    private String specificationFileUrl;

    public UnspecifiedUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public UnspecifiedUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
