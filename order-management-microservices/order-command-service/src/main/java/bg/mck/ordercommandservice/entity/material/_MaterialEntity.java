package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "materials")
public class _MaterialEntity extends BaseEntity {

    private MaterialType materialType;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<FastenerEntity> fasteners;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<GalvanisedSheetEntity> galvanisedSheets;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<InsulationEntity> insulation;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<MetalEntity> metals;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<PanelsEntity> panels;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<RebarEntity> rebars;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<SetEntity> sets;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<UnspecifiedEntity> unspecified;

    public _MaterialEntity() {
    }

    public _MaterialEntity(Set<FastenerEntity> fasteners, Set<GalvanisedSheetEntity> galvanisedSheets) {
        this.fasteners = fasteners;
        this.galvanisedSheets = galvanisedSheets;
    }

    public Set<FastenerEntity> getFasteners() {
        return fasteners;
    }

    public _MaterialEntity setFasteners(Set<FastenerEntity> fasteners) {
        this.fasteners = fasteners;
        return this;
    }

    public Set<GalvanisedSheetEntity> getGalvanisedSheets() {
        return galvanisedSheets;
    }

    public _MaterialEntity setGalvanisedSheets(Set<GalvanisedSheetEntity> galvanisedSheets) {
        this.galvanisedSheets = galvanisedSheets;
        return this;
    }
}
