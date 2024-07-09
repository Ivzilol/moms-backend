package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDTO {


    @Size(min = 10, message = "Order description must be at least 10 characters long.")
    private String orderDescription;

    private ZonedDateTime orderDate;

    @NotNull(message = "Delivery date must not be empty.")
    @Future(message = "Delivery date must be in the future.")
    private ZonedDateTime deliveryDate;

    @NotNull(message = "Construction site must not be empty.")
    @Valid
    private ConstructionSiteDTO constructionSite;

    private OrderStatus orderStatus;

    private MaterialType materialType;

    private Set<@Valid FastenerDTO> fasteners;
    private Set<@Valid GalvanisedSheetDTO> galvanisedSheets;
    private Set<@Valid InsulationDTO> insulation;
    private Set<@Valid MetalDTO> metals;
    private Set<@Valid PanelDTO> panels;
    private Set<@Valid RebarDTO> rebars;
    private Set<@Valid SetDTO> sets;
    private Set<@Valid UnspecifiedDTO> unspecified;
    private Set<@Valid ServiceDTO> services;
    private Set<@Valid TransportDTO> transports;

    public OrderDTO() {
    }

    public OrderDTO(String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, ConstructionSiteDTO constructionSite, OrderStatus orderStatus, MaterialType materialType, Set<@Valid FastenerDTO> fasteners, Set<@Valid GalvanisedSheetDTO> galvanisedSheets, Set<@Valid InsulationDTO> insulation, Set<@Valid MetalDTO> metals, Set<@Valid PanelDTO> panels, Set<@Valid RebarDTO> rebars, Set<@Valid SetDTO> sets, Set<@Valid UnspecifiedDTO> unspecified, Set<@Valid ServiceDTO> services, Set<@Valid TransportDTO> transports) {
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

    public Set<@Valid FastenerDTO> getFasteners() {
        return fasteners;
    }

    public OrderDTO setFasteners(Set<@Valid FastenerDTO> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<@Valid GalvanisedSheetDTO> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public OrderDTO setGalvanisedSheets(Set<@Valid GalvanisedSheetDTO> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public Set<@Valid InsulationDTO> getInsulation() {
        return insulation;
    }

    public OrderDTO setInsulation(Set<@Valid InsulationDTO> insulation) {
        this.insulation = insulation;
        return this;
    }

    public Set<@Valid MetalDTO> getMetals() {
        return metals;
    }

    public OrderDTO setMetals(Set<@Valid MetalDTO> metals) {
        this.metals = metals;
        return this;
    }

    public Set<@Valid PanelDTO> getPanels() {
        return panels;
    }

    public OrderDTO setPanels(Set<@Valid PanelDTO> panels) {
        this.panels = panels;
        return this;
    }

    public Set<@Valid RebarDTO> getRebars() {
        return rebars;
    }

    public OrderDTO setRebars(Set<@Valid RebarDTO> rebars) {
        this.rebars = rebars;
        return this;
    }

    public Set<@Valid SetDTO> getSets() {
        return sets;
    }

    public OrderDTO setSets(Set<@Valid SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public Set<@Valid UnspecifiedDTO> getUnspecified() {
        return unspecified;
    }

    public OrderDTO setUnspecified(Set<@Valid UnspecifiedDTO> unspecified) {
        this.unspecified = unspecified;
        return this;
    }

    public Set<@Valid ServiceDTO> getServices() {
        return services;
    }

    public OrderDTO setServices(Set<@Valid ServiceDTO> services) {
        this.services = services;
        return this;
    }

    public Set<@Valid TransportDTO> getTransports() {
        return transports;
    }

    public OrderDTO setTransports(Set<@Valid TransportDTO> transports) {
        this.transports = transports;
        return this;
    }
}
