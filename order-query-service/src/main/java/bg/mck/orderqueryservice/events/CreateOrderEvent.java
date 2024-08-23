package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

public class CreateOrderEvent<T> extends BaseEvent {

    private Long orderId;
    private String email;
    private String fullName;
    private Integer orderNumber;
    private String orderDescription;
    private ZonedDateTime orderDate;
    private ZonedDateTime deliveryDate;
    private MaterialType materialType;
    private OrderStatus orderStatus;
    private ConstructionSiteEntity constructionSite;
    private String specificationFileUrl;

    private Set<T> materials;

    public CreateOrderEvent() {}

    public CreateOrderEvent(Long orderId, String email, String fullName, Integer orderNumber, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, MaterialType materialType, OrderStatus orderStatus, ConstructionSiteEntity constructionSite, String specificationFileUrl, Set<T> materials) {
        this.orderId = orderId;
        this.email = email;
        this.fullName = fullName;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.materialType = materialType;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.specificationFileUrl = specificationFileUrl;
        this.materials = materials;
    }

    public CreateOrderEvent(OrderEventType eventType, LocalDateTime eventTime, Long orderId, String email, String fullName, Integer orderNumber, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, MaterialType materialType, OrderStatus orderStatus, ConstructionSiteEntity constructionSite, String specificationFileUrl, Set<T> materials) {
        super(eventType, eventTime);
        this.orderId = orderId;
        this.email = email;
        this.fullName = fullName;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.materialType = materialType;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.specificationFileUrl = specificationFileUrl;
        this.materials = materials;
    }

    public Long getOrderId() {
        return orderId;
    }

    public CreateOrderEvent<T> setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateOrderEvent<T> setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CreateOrderEvent<T> setFullName(String fullName) {
        this.fullName = fullName;
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

    public ConstructionSiteEntity getConstructionSite() {
        return constructionSite;
    }

    public CreateOrderEvent<T> setConstructionSite(ConstructionSiteEntity constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public CreateOrderEvent<T> setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
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