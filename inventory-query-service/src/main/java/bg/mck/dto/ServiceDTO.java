package bg.mck.dto;

public class ServiceDTO {

    private String id;
    private String name;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public ServiceDTO() {
    }

    public ServiceDTO(String id, String name, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public ServiceDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ServiceDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public ServiceDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
