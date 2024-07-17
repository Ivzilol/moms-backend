package bg.mck.ordercommandservice.event;

import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;

import java.time.ZonedDateTime;
import java.util.Set;

public class CreateOrderEvent<T> extends BaseEvent {

    private String email;
    private Integer orderNumber;
    private String orderDescription;
    private ZonedDateTime orderDate;
    private ZonedDateTime deliveryDate;
    private MaterialType materialType;
    private OrderStatus orderStatus;
    private ConstructionSiteEntity constructionSite;

    private Set<T> Materials;

    public CreateOrderEvent() {
    }

    public String getEmail() {
        return email;
    }

    public CreateOrderEvent<T> setEmail(String email) {
        this.email = email;
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

    public Set<T> getMaterials() {
        return Materials;
    }

    public CreateOrderEvent<T> setMaterials(Set<T> materials) {
        Materials = materials;
        return this;
    }
}