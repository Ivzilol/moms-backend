package bg.mck.ordercommandservice.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private String username;
    private String orderNumber;
    private String orderDescription;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String orderStatus;
    private ConstructionSiteDTO constructionSite;
    private Long materialsId;
    private ServiceDTO service;
    private TransportDTO transport;

    public OrderDTO() {
    }

    public OrderDTO(String username, String orderNumber, String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, String orderStatus, ConstructionSiteDTO constructionSite, Long materialsId, ServiceDTO service, TransportDTO transport) {
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.materialsId = materialsId;
        this.service = service;
        this.transport = transport;
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

    public ConstructionSiteDTO getConstructionSite() {
        return constructionSite;
    }

    public OrderDTO setConstructionSite(ConstructionSiteDTO constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public OrderDTO setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
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
