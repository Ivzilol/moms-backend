package bg.mck.events.transport;

import bg.mck.enums.EventType;

public class TransportRegisteredEvent extends BaseTransportEvent{
    private String name;
    private Double maxLength;
    private Double weight;
    private String Truck;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public TransportRegisteredEvent() {
    }

    public TransportRegisteredEvent(Long transportId, EventType eventType, String name, Double maxLength, Double weight, String truck, Double quantity, String description, String specificationFileUrl) {
        super(transportId, eventType);
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        Truck = truck;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getName() {
        return name;
    }

    public TransportRegisteredEvent setName(String name) {
        this.name = name;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public TransportRegisteredEvent setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public TransportRegisteredEvent setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return Truck;
    }

    public TransportRegisteredEvent setTruck(String truck) {
        Truck = truck;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public TransportRegisteredEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransportRegisteredEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public TransportRegisteredEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
