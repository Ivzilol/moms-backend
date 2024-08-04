package bg.mck.events.service;

import bg.mck.enums.EventType;

public class ServiceRegisteredEvent extends BaseServiceEvent{

    private String name;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public ServiceRegisteredEvent() {
    }

    public ServiceRegisteredEvent(Long serviceId, EventType eventType, String name, Double quantity, String description, String specificationFileUrl) {
        super(serviceId, eventType);
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getName() {
        return name;
    }

    public ServiceRegisteredEvent setName(String name) {
        this.name = name;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ServiceRegisteredEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceRegisteredEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public ServiceRegisteredEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
