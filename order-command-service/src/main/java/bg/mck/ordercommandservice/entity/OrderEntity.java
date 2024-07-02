package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import bg.mck.ordercommandservice.entity.service.ServiceEntity;
import bg.mck.ordercommandservice.entity.transport.TransportEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private String username;
    private Integer orderNumber;
    private String orderDescription;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private ConstructionSiteEntity constructionSite;

    @OneToOne(cascade = CascadeType.PERSIST)
    private _MaterialEntity materials;
    @OneToOne(cascade = CascadeType.PERSIST)
    private ServiceEntity services;
    @OneToOne(cascade = CascadeType.PERSIST)
    private TransportEntity transports;


    public OrderEntity() {
    }

    public OrderEntity(String username, Integer orderNumber, String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, OrderStatus orderStatus, ConstructionSiteEntity constructionSite, _MaterialEntity materials, ServiceEntity services, TransportEntity transports) {
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.materials = materials;
        this.services = services;
        this.transports = transports;
    }

    public String getUsername() {
        return username;
    }

    public OrderEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public OrderEntity setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrderEntity setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderEntity setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public OrderEntity setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderEntity setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public ConstructionSiteEntity getConstructionSite() {
        return constructionSite;
    }

    public OrderEntity setConstructionSite(ConstructionSiteEntity constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public _MaterialEntity getMaterials() {
        return materials;
    }

    public OrderEntity setMaterials(_MaterialEntity materials) {
        this.materials = materials;
        return this;
    }

    public ServiceEntity getServices() {
        return services;
    }

    public OrderEntity setServices(ServiceEntity services) {
        this.services = services;
        return this;
    }

    public TransportEntity getTransports() {
        return transports;
    }

    public OrderEntity setTransports(TransportEntity transports) {
        this.transports = transports;
        return this;
    }
}
