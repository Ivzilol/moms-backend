package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public class OrderDTO {

    private Long id;
    private Integer orderNumber;

    private String orderDescription;

    private ZonedDateTime orderDate;

    @NotNull(message = "Delivery date must not be empty.")
    private ZonedDateTime deliveryDate;

    @NotNull(message = "Construction site must not be empty.")
    @Valid
    private ConstructionSiteDTO constructionSite;

    private OrderStatus orderStatus;

    private MaterialType materialType;
    private String specificationFileUrl;

    private List<@Valid FastenerDTO> fasteners;
    private List<@Valid GalvanisedSheetDTO> galvanisedSheets;
    private List<@Valid InsulationDTO> insulation;
    private List<@Valid MetalDTO> metals;
    private List<@Valid PanelDTO> panels;
    private List<@Valid RebarDTO> rebars;
    private List<@Valid SetDTO> sets;
    private List<@Valid UnspecifiedDTO> unspecified;
    private List<@Valid ServiceDTO> services;
    private List<@Valid TransportDTO> transports;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Integer orderNumber, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, ConstructionSiteDTO constructionSite, OrderStatus orderStatus, MaterialType materialType, String specificationFileUrl, List<@Valid FastenerDTO> fasteners, List<@Valid GalvanisedSheetDTO> galvanisedSheets, List<@Valid InsulationDTO> insulation, List<@Valid MetalDTO> metals, List<@Valid PanelDTO> panels, List<@Valid RebarDTO> rebars, List<@Valid SetDTO> sets, List<@Valid UnspecifiedDTO> unspecified, List<@Valid ServiceDTO> services, List<@Valid TransportDTO> transports) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.constructionSite = constructionSite;
        this.orderStatus = orderStatus;
        this.materialType = materialType;
        this.specificationFileUrl = specificationFileUrl;
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

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public OrderDTO setOrderNumber(Integer orderNumber) {
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

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public OrderDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public List<@Valid FastenerDTO> getFasteners() {
        return fasteners;
    }

    public OrderDTO setFasteners(List<@Valid FastenerDTO> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public List<@Valid GalvanisedSheetDTO> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public OrderDTO setGalvanisedSheets(List<@Valid GalvanisedSheetDTO> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public List<@Valid InsulationDTO> getInsulation() {
        return insulation;
    }

    public OrderDTO setInsulation(List<@Valid InsulationDTO> insulation) {
        this.insulation = insulation;
        return this;
    }

    public List<@Valid MetalDTO> getMetals() {
        return metals;
    }

    public OrderDTO setMetals(List<@Valid MetalDTO> metals) {
        this.metals = metals;
        return this;
    }

    public List<@Valid PanelDTO> getPanels() {
        return panels;
    }

    public OrderDTO setPanels(List<@Valid PanelDTO> panels) {
        this.panels = panels;
        return this;
    }

    public List<@Valid RebarDTO> getRebars() {
        return rebars;
    }

    public OrderDTO setRebars(List<@Valid RebarDTO> rebars) {
        this.rebars = rebars;
        return this;
    }

    public List<@Valid SetDTO> getSets() {
        return sets;
    }

    public OrderDTO setSets(List<@Valid SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public List<@Valid UnspecifiedDTO> getUnspecified() {
        return unspecified;
    }

    public OrderDTO setUnspecified(List<@Valid UnspecifiedDTO> unspecified) {
        this.unspecified = unspecified;
        return this;
    }

    public List<@Valid ServiceDTO> getServices() {
        return services;
    }

    public OrderDTO setServices(List<@Valid ServiceDTO> services) {
        this.services = services;
        return this;
    }

    public List<@Valid TransportDTO> getTransports() {
        return transports;
    }

    public OrderDTO setTransports(List<@Valid TransportDTO> transports) {
        this.transports = transports;
        return this;
    }
}
