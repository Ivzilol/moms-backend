package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Окомплектовки/обшивки??
@Entity
@Table(name = "sets")
public class SetEntity  extends BaseEntity {

    @Column(columnDefinition="TEXT")
    private String description;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public SetEntity() {
    }

    public SetEntity(String description, Double quantity, String note, String specificationFileUrl) {
        this.description = description;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getDescription() {
        return description;
    }

    public SetEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SetEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
