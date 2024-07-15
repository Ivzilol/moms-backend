package bg.mck.orderqueryservice.dto;

public class BaseDTO {

    private Long id;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public BaseDTO() {
    }

    public BaseDTO(Long id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public Long getId() {
        return id;
    }

    public BaseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BaseDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BaseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public BaseDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
