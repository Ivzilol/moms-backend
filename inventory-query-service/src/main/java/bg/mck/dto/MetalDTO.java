package bg.mck.dto;

public class MetalDTO implements MaterialDTO{

    private String id;

    private String name;

    private Double totalWeight;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public MetalDTO() {
    }

    public MetalDTO(String id, String name, Double totalWeight, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.totalWeight = totalWeight;
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

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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
