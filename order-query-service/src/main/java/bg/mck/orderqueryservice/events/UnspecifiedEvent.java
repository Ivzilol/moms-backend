package bg.mck.orderqueryservice.events;

public class UnspecifiedEvent {
    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public UnspecifiedEvent() {
    }

    public UnspecifiedEvent(Long id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public Long getId() {
        return id;
    }

    public UnspecifiedEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
