package bg.mck.orderqueryservice.events;

public class RebarEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String maxLength;
    private String weight;

    public RebarEvent() {
    }

    public RebarEvent(Long id, Double quantity, String description, String specificationFileUrl, String maxLength, String weight) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.maxLength = maxLength;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public RebarEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RebarEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarEvent setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
