package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.dto.Material.*;
import bg.mck.ordercommandservice.dto.errorDTO.FastenerErrorDTO;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
    @Size(min = 10, message = "Order description must be at least 10 characters long.")
    private String orderDescription;
    @NotNull(message = "Order date must not be empty.")
    @FutureOrPresent(message = "Order date must be in the present or future.")
    private LocalDateTime orderDate;
    @NotNull(message = "Delivery date must not be empty.")
    @Future(message = "Delivery date must be in the future.")
    private LocalDateTime deliveryDate;

    @NotNull(message = "Construction site must not be empty.")
    @Valid
    private ConstructionSiteDTO constructionSite;

    private MaterialType materialType;

    private Set<FastenerErrorDTO> fasteners;
    private Set<GalvanisedSheetDTO> galvanisedSheets;
    private Set<InsulationDTO> insulation;
    private Set<MetalDTO> metals;
    private Set<PanelDTO> panels;
    private Set<RebarDTO> rebars;
    private Set<SetDTO> sets;
    private Set<UnspecifiedDTO> unspecified;

    @Valid
    private ServiceDTO service;
    @Valid
    private TransportDTO transport;

    public OrderDTO() {
    }

    public OrderDTO(String orderDescription, LocalDateTime orderDate, LocalDateTime deliveryDate, ConstructionSiteDTO constructionSite, MaterialType materialType, Set<FastenerErrorDTO> fasteners, Set<GalvanisedSheetDTO> galvanisedSheets, Set<InsulationDTO> insulation, Set<MetalDTO> metals, Set<PanelDTO> panels, Set<RebarDTO> rebars, Set<SetDTO> sets, Set<UnspecifiedDTO> unspecified, ServiceDTO service, TransportDTO transport) {
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.constructionSite = constructionSite;
        this.materialType = materialType;
        this.fasteners = fasteners;
        this.galvanisedSheets = galvanisedSheets;
        this.insulation = insulation;
        this.metals = metals;
        this.panels = panels;
        this.rebars = rebars;
        this.sets = sets;
        this.unspecified = unspecified;
        this.service = service;
        this.transport = transport;
    }

    public @Size(min = 10, message = "Order description must be at least 10 characters long.") String getOrderDescription() {
        return orderDescription;
    }

    public OrderDTO setOrderDescription(@Size(min = 10, message = "Order description must be at least 10 characters long.") String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public @NotNull(message = "Order date must not be empty.") @FutureOrPresent(message = "Order date must be in the present or future.") LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderDTO setOrderDate(@NotNull(message = "Order date must not be empty.") @FutureOrPresent(message = "Order date must be in the present or future.") LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public @NotNull(message = "Delivery date must not be empty.") @Future(message = "Delivery date must be in the future.") LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public OrderDTO setDeliveryDate(@NotNull(message = "Delivery date must not be empty.") @Future(message = "Delivery date must be in the future.") LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public @NotNull(message = "Construction site must not be empty.") @Valid ConstructionSiteDTO getConstructionSite() {
        return constructionSite;
    }

    public OrderDTO setConstructionSite(@NotNull(message = "Construction site must not be empty.") @Valid ConstructionSiteDTO constructionSite) {
        this.constructionSite = constructionSite;
        return this;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public OrderDTO setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Set<FastenerErrorDTO> getFasteners() {
        return fasteners;
    }

    public OrderDTO setFasteners(Set<FastenerErrorDTO> fasteners) {
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

    public @Valid ServiceDTO getService() {
        return service;
    }

    public OrderDTO setService(@Valid ServiceDTO service) {
        this.service = service;
        return this;
    }

    public @Valid TransportDTO getTransport() {
        return transport;
    }

    public OrderDTO setTransport(@Valid TransportDTO transport) {
        this.transport = transport;
        return this;
    }
}
