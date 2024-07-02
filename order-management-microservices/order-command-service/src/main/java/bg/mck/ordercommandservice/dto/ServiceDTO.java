package bg.mck.ordercommandservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class ServiceDTO {
    private Long id;
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters.")
    private String description;
    @DecimalMin(value = "0.00", message = "Price must be a positive number.")
    private Double price;

    public ServiceDTO() {
    }

    public ServiceDTO(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public ServiceDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceDTO setPrice(Double price) {
        this.price = price;
        return this;
    }
}
