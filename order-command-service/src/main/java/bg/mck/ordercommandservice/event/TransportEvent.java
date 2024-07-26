package bg.mck.ordercommandservice.event;

public class TransportEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String maxLength;
    private String weight;
    private String truck;

    public TransportEvent() {
    }

    public TransportEvent(Long id, Double quantity, String description, String specificationFileUrl, String maxLength, String weight, String truck) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
    }

    public Long getId() {
        return id;
    }

    public TransportEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public TransportEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransportEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public TransportEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public TransportEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public TransportEvent setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportEvent setTruck(String truck) {
        this.truck = truck;
        return this;
    }
}
