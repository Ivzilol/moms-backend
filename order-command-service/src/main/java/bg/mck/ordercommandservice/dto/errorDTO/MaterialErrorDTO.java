package bg.mck.ordercommandservice.dto.errorDTO;

import bg.mck.ordercommandservice.dto.Material.*;
import bg.mck.ordercommandservice.entity.enums.MaterialType;

import java.util.HashSet;
import java.util.Set;

public class MaterialErrorDTO {
    private MaterialType materialType;

    private Set<FastenerErrorDTO> fasteners;
    private Set<GalvanisedSheetDTO> galvanisedSheets;
    private Set<InsulationDTO> insulation;
    private Set<MetalDTO> metals;
    private Set<PanelDTO> panels;
    private Set<RebarDTO> rebars;
    private Set<SetDTO> sets;
    private Set<UnspecifiedDTO> unspecified;

    public MaterialErrorDTO() {
    }

    public MaterialErrorDTO(MaterialType materialType, Set<FastenerErrorDTO> fasteners, Set<GalvanisedSheetDTO> galvanisedSheets, Set<InsulationDTO> insulation, Set<MetalDTO> metals, Set<PanelDTO> panels, Set<RebarDTO> rebars, Set<SetDTO> sets, Set<UnspecifiedDTO> unspecified) {
        this.materialType = materialType;
        this.fasteners = new HashSet<>();
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

    public MaterialErrorDTO setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Set<FastenerErrorDTO> getFasteners() {
        return fasteners;
    }

    public MaterialErrorDTO setFasteners(Set<FastenerErrorDTO> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<GalvanisedSheetDTO> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public MaterialErrorDTO setGalvanisedSheets(Set<GalvanisedSheetDTO> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }

    public Set<InsulationDTO> getInsulation() {
        return insulation;
    }

    public MaterialErrorDTO setInsulation(Set<InsulationDTO> insulation) {
        this.insulation = insulation;
        return this;
    }

    public Set<MetalDTO> getMetals() {
        return metals;
    }

    public MaterialErrorDTO setMetals(Set<MetalDTO> metals) {
        this.metals = metals;
        return this;
    }

    public Set<PanelDTO> getPanels() {
        return panels;
    }

    public MaterialErrorDTO setPanels(Set<PanelDTO> panels) {
        this.panels = panels;
        return this;
    }

    public Set<RebarDTO> getRebars() {
        return rebars;
    }

    public MaterialErrorDTO setRebars(Set<RebarDTO> rebars) {
        this.rebars = rebars;
        return this;
    }

    public Set<SetDTO> getSets() {
        return sets;
    }

    public MaterialErrorDTO setSets(Set<SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public Set<UnspecifiedDTO> getUnspecified() {
        return unspecified;
    }

    public MaterialErrorDTO setUnspecified(Set<UnspecifiedDTO> unspecified) {
        this.unspecified = unspecified;
        return this;
    }
}
