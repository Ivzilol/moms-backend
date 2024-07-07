package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @NotNull
    private String username;

    private Integer orderNumber;

    @Column(columnDefinition = "TEXT")
    private String orderDescription;

    @FutureOrPresent
    private LocalDateTime orderDate;

    @Future
    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private ConstructionSiteEntity constructionSite;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<FastenerEntity> fasteners;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<GalvanisedSheetEntity> galvanisedSheets;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<InsulationEntity> insulation;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<MetalEntity> metals;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<PanelEntity> panels;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<RebarEntity> rebars;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<SetEntity> sets;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UnspecifiedEntity> unspecified;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ServiceEntity> services;
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<TransportEntity> transports;


    public OrderEntity() {
    }

    public OrderEntity(String username, Integer orderNumber, String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, OrderStatus orderStatus, ConstructionSiteEntity constructionSite, Set<FastenerEntity> fasteners, Set<GalvanisedSheetEntity> galvanisedSheets, Set<InsulationEntity> insulation, Set<MetalEntity> metals, Set<PanelEntity> panels, Set<RebarEntity> rebars, Set<SetEntity> sets, Set<UnspecifiedEntity> unspecified, Set<ServiceEntity> services, Set<TransportEntity> transports) {
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.constructionSite = constructionSite;
        this.fasteners = fasteners;
        this.galvanisedSheets = galvanisedSheets;
        this.insulation = insulation;
        this.metals = metals;
        this.panels = panels;
        this.rebars = rebars;
        this.sets = sets;
        this.unspecified = unspecified;
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

    public Set<FastenerEntity> getFasteners() {
        return fasteners;
    }

    public OrderEntity setFasteners(Set<FastenerEntity> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<GalvanisedSheetEntity> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public OrderEntity setGalvanisedSheets(Set<GalvanisedSheetEntity> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public Set<InsulationEntity> getInsulation() {
        return insulation;
    }

    public OrderEntity setInsulation(Set<InsulationEntity> insulation) {
        this.insulation = insulation;
        return this;
    }

    public Set<MetalEntity> getMetals() {
        return metals;
    }

    public OrderEntity setMetals(Set<MetalEntity> metals) {
        this.metals = metals;
        return this;
    }

    public Set<PanelEntity> getPanels() {
        return panels;
    }

    public OrderEntity setPanels(Set<PanelEntity> panels) {
        this.panels = panels;
        return this;
    }

    public Set<RebarEntity> getRebars() {
        return rebars;
    }

    public OrderEntity setRebars(Set<RebarEntity> rebars) {
        this.rebars = rebars;
        return this;
    }

    public Set<SetEntity> getSets() {
        return sets;
    }

    public OrderEntity setSets(Set<SetEntity> sets) {
        this.sets = sets;
        return this;
    }

    public Set<UnspecifiedEntity> getUnspecified() {
        return unspecified;
    }

    public OrderEntity setUnspecified(Set<UnspecifiedEntity> unspecified) {
        this.unspecified = unspecified;
        return this;
    }

    public Set<ServiceEntity> getServices() {
        return services;
    }

    public OrderEntity setServices(Set<ServiceEntity> services) {
        this.services = services;
        return this;
    }

    public Set<TransportEntity> getTransports() {
        return transports;
    }

    public OrderEntity setTransports(Set<TransportEntity> transports) {
        this.transports = transports;
        return this;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public OrderEntity setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }
}
