package bg.mck.orderqueryservice.events;


import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;


public class CreateOrderEvent<T> extends BaseEvent {


    private String username;
    private Integer orderNumber;
    private String orderDescription;
    private ZonedDateTime orderDate;
    private ZonedDateTime deliveryDate;
    private MaterialType materialType;
    private OrderStatus orderStatus;
    private ConstructionSiteDTO constructionSite;

    private Set<T> materials;

    public CreateOrderEvent() {
    }

    public CreateOrderEvent(OrderEventType eventType, Long orderId, LocalDateTime localDateTime, String username, Integer orderNumber, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, MaterialType materialType, OrderStatus orderStatus, ConstructionSiteDTO constructionSite, Set<T> materials) {
        super(eventType, orderId, localDateTime);
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.materialType = materialType;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.materials = materials;
    }

    public String getUsername() {
        return username;
    }

    public CreateOrderEvent<T> setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public CreateOrderEvent<T> setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public CreateOrderEvent<T> setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public CreateOrderEvent<T> setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public CreateOrderEvent<T> setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public CreateOrderEvent<T> setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public CreateOrderEvent<T> setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public ConstructionSiteDTO getConstructionSite() {
        return constructionSite;
    }

    public CreateOrderEvent<T> setConstructionSite(ConstructionSiteDTO constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public Set<T> getMaterials() {
        return materials;
    }

    public CreateOrderEvent<T> setMaterials(Set<T> materials) {
        this.materials = materials;
        return this;
    }
}
