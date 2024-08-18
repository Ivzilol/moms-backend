package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("unspecified")
public class UnspecifiedEntity  extends BaseMaterialEntity {

    private String quantity;

    public UnspecifiedEntity() {
    }

    public UnspecifiedEntity(String quantity) {
        this.quantity = quantity;
    }

    public UnspecifiedEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.quantity = quantity;
    }

    public UnspecifiedEntity(String note, String specificationFileUrl, String quantity) {
        super(note, specificationFileUrl);
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public UnspecifiedEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
