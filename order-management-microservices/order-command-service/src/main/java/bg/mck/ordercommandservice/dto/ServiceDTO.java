package bg.mck.ordercommandservice.dto;

public class ServiceDTO {
    private Long id;
    private String description;
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
