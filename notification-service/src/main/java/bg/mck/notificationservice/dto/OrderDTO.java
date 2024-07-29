package bg.mck.notificationservice.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDTO {


    private String orderDescription;

    private String orderNumber;

    private ZonedDateTime orderDate;

    private ZonedDateTime deliveryDate;

    private String constructionSiteName;

    private String constructionSiteNumber;

    private String orderStatus;

    private String materialType;

    private String specificationFileUrl;

    private Set<Object> materials;

    private boolean isNewOrder;

    private String email;

    public OrderDTO(String orderNumber, String specificationFileUrl, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, String constructionSite, String constructionSiteNumber, String orderStatus, String materialType, Set<Object> materials, boolean isNewOrder, String email) {
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.constructionSiteName = constructionSite;
        this.specificationFileUrl = specificationFileUrl;
        this.constructionSiteNumber = constructionSiteNumber;
        this.orderStatus = orderStatus;
        this.materialType = materialType;
        this.materials = materials;
        this.isNewOrder = isNewOrder;
        this.email = email;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderDTO setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrderDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public OrderDTO setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public OrderDTO setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public OrderDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getConstructionSiteName() {
        return constructionSiteName;
    }

    public String getConstructionSiteNumber() {
        return constructionSiteNumber;
    }

    public OrderDTO setConstructionSiteNumber(String constructionSiteNumber) {
        this.constructionSiteNumber = constructionSiteNumber;
        return this;
    }

    public OrderDTO setConstructionSiteName(String constructionSiteName) {
        this.constructionSiteName = constructionSiteName;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderDTO setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public OrderDTO setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public Set<Object> getMaterials() {
        return materials;
    }

    public OrderDTO setMaterials(Set<Object> materials) {
        this.materials = materials;
        return this;
    }

    public boolean isNewOrder() {
        return isNewOrder;
    }

    public OrderDTO setNewOrder(boolean newOrder) {
        isNewOrder = newOrder;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
