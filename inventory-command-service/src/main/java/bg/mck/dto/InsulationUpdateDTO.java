package bg.mck.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class InsulationUpdateDTO {

    private String name;
    private String type;
    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    private Double thickness;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public InsulationUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public InsulationUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationUpdateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationUpdateDTO setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
