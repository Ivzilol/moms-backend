package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("services")
public class ServiceEntity extends BaseMaterialEntity {

    private String quantity;

    public ServiceEntity() {
    }

    public ServiceEntity(String quantity) {
        this.quantity = quantity;
    }

    public ServiceEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String quantity) {
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
