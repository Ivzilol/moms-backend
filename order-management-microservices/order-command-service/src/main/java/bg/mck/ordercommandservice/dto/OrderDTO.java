package bg.mck.ordercommandservice.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private String username;
    private String orderNumber;
    private String orderDescription;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String orderStatus;
    private Long constructionSiteId;
    private Long materialsId;
    private Long servicesId;
    private Long transportsId;

    public OrderDTO() {
    }

    public OrderDTO(String username, String orderNumber, String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, String orderStatus, Long constructionSiteId, Long materialsId, Long servicesId, Long transportsId) {
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.constructionSiteId = constructionSiteId;
        this.materialsId = materialsId;
        this.servicesId = servicesId;
        this.transportsId = transportsId;
    }

    public String getUsername() {
        return username;
    }

    public OrderDTO setUsername(String username) {
        this.username = username;
        return this;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderDTO setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Long getConstructionSiteId() {
        return constructionSiteId;
    }

    public OrderDTO setConstructionSiteId(Long constructionSiteId) {
        this.constructionSiteId = constructionSiteId;
        return this;
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public OrderDTO setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
        return this;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public OrderDTO setServicesId(Long servicesId) {
        this.servicesId = servicesId;
        return this;
    }

    public Long getTransportsId() {
        return transportsId;
    }

    public OrderDTO setTransportsId(Long transportsId) {
        this.transportsId = transportsId;
        return this;
    }
}
