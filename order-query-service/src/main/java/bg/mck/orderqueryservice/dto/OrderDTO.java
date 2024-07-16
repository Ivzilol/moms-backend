package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDTO {


    private String orderDescription;

    private ZonedDateTime orderDate;

    private ZonedDateTime deliveryDate;

    private ConstructionSiteDTO constructionSite;

    private OrderStatus orderStatus;

    private MaterialType materialType;

    private Set<FastenerDTO> fasteners;
    private Set<GalvanisedSheetDTO> galvanisedSheets;
    private Set<InsulationDTO> insulation;
    private Set<MetalDTO> metals;
    private Set<PanelDTO> panels;
    private Set<RebarDTO> rebars;
    private Set<SetDTO> sets;
    private Set<UnspecifiedDTO> unspecified;
    private Set<ServiceDTO> services;
    private Set<TransportDTO> transports;

    public OrderDTO() {
    }

    public OrderDTO(String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, ConstructionSiteDTO constructionSite, OrderStatus orderStatus, MaterialType materialType, Set<FastenerDTO> fasteners, Set<GalvanisedSheetDTO> galvanisedSheets, Set<InsulationDTO> insulation, Set<MetalDTO> metals, Set<PanelDTO> panels, Set<RebarDTO> rebars, Set<SetDTO> sets, Set<UnspecifiedDTO> unspecified, Set<ServiceDTO> services, Set<TransportDTO> transports) {
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.constructionSite = constructionSite;
        this.orderStatus = orderStatus;
        this.materialType = materialType;
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

    public ConstructionSiteDTO getConstructionSite() {
        return constructionSite;
    }

    public OrderDTO setConstructionSite(ConstructionSiteDTO constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderDTO setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public OrderDTO setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Set<FastenerDTO> getFasteners() {
        return fasteners;
    }

    public OrderDTO setFasteners(Set<FastenerDTO> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<GalvanisedSheetDTO> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public OrderDTO setGalvanisedSheets(Set<GalvanisedSheetDTO> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public Set<InsulationDTO> getInsulation() {
        return insulation;
    }

    public OrderDTO setInsulation(Set<InsulationDTO> insulation) {
        this.insulation = insulation;
        return this;
    }

    public Set<MetalDTO> getMetals() {
        return metals;
    }

    public OrderDTO setMetals(Set<MetalDTO> metals) {
        this.metals = metals;
        return this;
    }

    public Set<PanelDTO> getPanels() {
        return panels;
    }

    public OrderDTO setPanels(Set<PanelDTO> panels) {
        this.panels = panels;
        return this;
    }

    public Set<RebarDTO> getRebars() {
        return rebars;
    }

    public OrderDTO setRebars(Set<RebarDTO> rebars) {
        this.rebars = rebars;
        return this;
    }

    public Set<SetDTO> getSets() {
        return sets;
    }

    public OrderDTO setSets(Set<SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public Set<UnspecifiedDTO> getUnspecified() {
        return unspecified;
    }

    public OrderDTO setUnspecified(Set<UnspecifiedDTO> unspecified) {
        this.unspecified = unspecified;
        return this;
    }

    public Set<ServiceDTO> getServices() {
        return services;
    }

    public OrderDTO setServices(Set<ServiceDTO> services) {
        this.services = services;
        return this;
    }

    public Set<TransportDTO> getTransports() {
        return transports;
    }

    public OrderDTO setTransports(Set<TransportDTO> transports) {
        this.transports = transports;
        return this;
    }
}
