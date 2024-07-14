package bg.mck.dto;

public class RebarDTO implements MaterialDTO{

    private String id;

    private String name;

    private Double maxLength;

    private Double weight;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RebarDTO() {
    }

    public RebarDTO(String id, String name, Double maxLength, Double weight, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
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

    public Double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
