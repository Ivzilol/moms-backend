package bg.mck.dto;

public class UnspecifiedDTO implements MaterialDTO{

    private String id;

    private String name;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(String id, String name, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
