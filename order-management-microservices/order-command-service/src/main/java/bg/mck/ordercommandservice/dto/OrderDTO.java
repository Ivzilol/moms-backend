package bg.mck.ordercommandservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class OrderDTO {
    @Size(min = 10, message = "Order description must be at least 10 characters long.")
    private String orderDescription;
    @NotNull(message = "Order date must not be empty.")
    @FutureOrPresent(message = "Order date must be in the present or future.")
    private LocalDateTime orderDate;
    @NotNull(message = "Delivery date must not be empty.")
    @Future(message = "Delivery date must be in the future.")
    private LocalDateTime deliveryDate;
    @NotNull(message = "Construction site must not be empty.")
    @Valid
    private ConstructionSiteDTO constructionSite;
    @Valid
    private MaterialDTO material;
    @Valid
    private ServiceDTO service;
    @Valid
    private TransportDTO transport;

    public OrderDTO() {
    }

    public OrderDTO(String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, ConstructionSiteDTO constructionSite, MaterialDTO material, ServiceDTO service, TransportDTO transport) {
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.constructionSite = constructionSite;
        this.material = material;
        this.service = service;
        this.transport = transport;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrderDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderDTO setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public OrderDTO setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public ConstructionSiteDTO getConstructionSite() {
        return constructionSite;
    }

    public OrderDTO setConstructionSite(ConstructionSiteDTO constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public OrderDTO setMaterial(MaterialDTO material) {
        this.material = material;
        return this;
    }

    public ServiceDTO getService() {
        return service;
    }

    public OrderDTO setService(ServiceDTO service) {
        this.service = service;
        return this;
    }

    public TransportDTO getTransport() {
        return transport;
    }

    public OrderDTO setTransport(TransportDTO transport) {
        this.transport = transport;
        return this;
    }
}
