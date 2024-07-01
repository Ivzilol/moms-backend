package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.dto.Material.*;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import jakarta.validation.Valid;

import java.util.Set;

public class MaterialDTO {

    private MaterialType materialType;

    private Set<@Valid FastenerDTO> fasteners;
    private Set<GalvanisedSheetDTO> galvanisedSheets;
    private Set<InsulationDTO> insulation;
    private Set<MetalDTO> metals;
    private Set<PanelDTO> panels;
    private Set<RebarDTO> rebars;
    private Set<SetDTO> sets;
    private Set<UnspecifiedDTO> unspecified;

    public MaterialDTO() {
    }

    public MaterialDTO(MaterialType materialType, Set<FastenerDTO> fasteners, Set<GalvanisedSheetDTO> galvanisedSheets, Set<InsulationDTO> insulation, Set<MetalDTO> metals, Set<PanelDTO> panels, Set<RebarDTO> rebars, Set<SetDTO> sets, Set<UnspecifiedDTO> unspecified) {
        this.materialType = materialType;
        this.fasteners = fasteners;
        this.galvanisedSheets = galvanisedSheets;
        this.insulation = insulation;
        this.metals = metals;
        this.panels = panels;
        this.rebars = rebars;
        this.sets = sets;
        this.unspecified = unspecified;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public MaterialDTO setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Set<FastenerDTO> getFasteners() {
        return fasteners;
    }

    public MaterialDTO setFasteners(Set<FastenerDTO> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<GalvanisedSheetDTO> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public MaterialDTO setGalvanisedSheets(Set<GalvanisedSheetDTO> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public Set<InsulationDTO> getInsulation() {
        return insulation;
    }

    public MaterialDTO setInsulation(Set<InsulationDTO> insulation) {
        this.insulation = insulation;
        return this;
    }

    public Set<MetalDTO> getMetals() {
        return metals;
    }

    public MaterialDTO setMetals(Set<MetalDTO> metals) {
        this.metals = metals;
        return this;
    }

    public Set<PanelDTO> getPanels() {
        return panels;
    }

    public MaterialDTO setPanels(Set<PanelDTO> panels) {
        this.panels = panels;
        return this;
    }

    public Set<RebarDTO> getRebars() {
        return rebars;
    }

    public MaterialDTO setRebars(Set<RebarDTO> rebars) {
        this.rebars = rebars;
        return this;
    }

    public Set<SetDTO> getSets() {
        return sets;
    }

    public MaterialDTO setSets(Set<SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public Set<UnspecifiedDTO> getUnspecified() {
        return unspecified;
    }

    public MaterialDTO setUnspecified(Set<UnspecifiedDTO> unspecified) {
        this.unspecified = unspecified;
        return this;
    }
}
