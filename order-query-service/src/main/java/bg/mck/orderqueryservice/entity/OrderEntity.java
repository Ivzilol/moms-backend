package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.Set;

@Document("orders")
public class OrderEntity extends BaseEntity {

    @NotNull
    private String email;

    @Field(name = "order_number")
    @Indexed(unique = true)
    private Integer orderNumber;

    @Field(name = "order_description")
    @Size(max = 10000)
    private String orderDescription;


    @Field(name = "order_date")
    private ZonedDateTime orderDate;


    private String specificationFileUrl;

    @Future(message = "Delivery date must be in the future.")
    @Field(name = "delivery_date")
    @NotNull
    private ZonedDateTime deliveryDate;

    private MaterialType materialType;

    @Field(name = "order_status")
    @NotNull
    private OrderStatus orderStatus;

    private ConstructionSiteEntity constructionSite;

    private Set<FastenerEntity> fasteners;

    private Set<GalvanisedSheetEntity> galvanisedSheets;

    private Set<InsulationEntity> insulation;

    private Set<MetalEntity> metals;

    private Set<PanelEntity> panels;

    private Set<RebarEntity> rebars;

    private Set<SetEntity> sets;

    private Set<UnspecifiedEntity> unspecified;

    private Set<ServiceEntity> services;

    private Set<TransportEntity> transports;


    public OrderEntity() {
    }

    public OrderEntity(String id, String email, Integer orderNumber, String orderDescription, ZonedDateTime orderDate, String specificationFileUrl, ZonedDateTime deliveryDate, MaterialType materialType, OrderStatus orderStatus, ConstructionSiteEntity constructionSite, Set<FastenerEntity> fasteners, Set<GalvanisedSheetEntity> galvanisedSheets, Set<InsulationEntity> insulation, Set<MetalEntity> metals, Set<PanelEntity> panels, Set<RebarEntity> rebars, Set<SetEntity> sets, Set<UnspecifiedEntity> unspecified, Set<ServiceEntity> services, Set<TransportEntity> transports) {
        super(id);
        this.email = email;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.specificationFileUrl = specificationFileUrl;
        this.deliveryDate = deliveryDate;
        this.materialType = materialType;
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

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public OrderEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderEntity setEmail(String username) {
        this.email = username;
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

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public OrderEntity setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public OrderEntity setDeliveryDate(ZonedDateTime deliveryDate) {
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
