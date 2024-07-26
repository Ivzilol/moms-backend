package bg.mck.orderqueryservice.events;

public class MetalEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String totalWeight;

    public MetalEvent() {
    }

    public MetalEvent(Long id, Double quantity, String description, String specificationFileUrl, String totalWeight) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.totalWeight = totalWeight;
    }

    public Long getId() {
        return id;
    }

    public MetalEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalEvent setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }
}
