package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceEntity extends BaseMaterialEntity {
    private String quantity;

    public ServiceEntity() {
    }

    public ServiceEntity(String quantity) {
        this.quantity = quantity;
    }

    public ServiceEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.quantity = quantity;
    }

    public ServiceEntity(String note, String specificationFileUrl, String quantity) {
        super(note, specificationFileUrl);
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public ServiceEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
