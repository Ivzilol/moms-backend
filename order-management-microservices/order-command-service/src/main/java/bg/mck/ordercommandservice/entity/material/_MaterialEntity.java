package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "materials")
public class _MaterialEntity extends BaseEntity {
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<FastenerEntity> fasteners;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<GalvanisedSheetEntity> galvanisedSheets;

    //    private Set<InsulationEntity> insulation;
//    private Set<MetalEntity> metals;
//    private Set<PanelsEntity> panels;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Set<RebarEntity> rebars;
//    private Set<SetEntity> sets;
//    private Set<UnspecifiedEntity> unspecified;

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
