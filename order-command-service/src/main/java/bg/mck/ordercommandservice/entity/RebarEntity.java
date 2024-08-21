package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseMaterialEntity {

    private String maxLength;
    @Column(nullable = false)
    private String quantity;

    public RebarEntity() {

    }

    public RebarEntity(String maxLength, String quantity) {
        this.maxLength = maxLength;
        this.quantity = quantity;
    }

    public RebarEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String maxLength, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.quantity = quantity;
    }

    public RebarEntity(String note, String specificationFileUrl, String maxLength, String quantity) {
        super(note, specificationFileUrl);
        this.maxLength = maxLength;
        this.quantity = quantity;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
