package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import jakarta.validation.Valid;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDTO {

    private String id;

    private String orderDescription;

    private ZonedDateTime orderDate;

    private ZonedDateTime deliveryDate;

    private ConstructionSiteDTO constructionSite;

    private OrderStatus orderStatus;

    private Integer orderNumber;

    private MaterialType materialType;

    private String specificationFileUrl;

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

    public OrderDTO(String id, String specificationFileUrl, String orderDescription, ZonedDateTime orderDate, ZonedDateTime deliveryDate, ConstructionSiteDTO constructionSite, OrderStatus orderStatus, MaterialType materialType, Set<FastenerDTO> fasteners, Set<GalvanisedSheetDTO> galvanisedSheets, Set<InsulationDTO> insulation, Set<MetalDTO> metals, Set<PanelDTO> panels, Set<RebarDTO> rebars, Set<SetDTO> sets, Set<UnspecifiedDTO> unspecified, Set<ServiceDTO> services, Set<TransportDTO> transports) {
        this.id = id;
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
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public OrderDTO setId(String id) {
        this.id = id;
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

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public OrderDTO setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public OrderDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", constructionSite=" + constructionSite +
                ", orderStatus=" + orderStatus +
                ", materialType=" + materialType +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                ", fasteners=" + fasteners +
                ", galvanisedSheets=" + galvanisedSheets +
                ", insulation=" + insulation +
                ", metals=" + metals +
                ", panels=" + panels +
                ", rebars=" + rebars +
                ", sets=" + sets +
                ", unspecified=" + unspecified +
                ", services=" + services +
                ", transports=" + transports +
                '}';
    }
}
