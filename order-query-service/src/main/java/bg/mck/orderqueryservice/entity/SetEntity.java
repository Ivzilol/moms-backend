package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sets")
public class SetEntity extends BaseMaterialEntity {

    private String quantity;
    private String color;
    private String maxLength;

    public SetEntity() {
    }

    public SetEntity(String quantity, String color, String maxLength) {
        this.quantity = quantity;
        this.color = color;
        this.maxLength = maxLength;
    }

    public SetEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String quantity, String color, String maxLength) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.quantity = quantity;
        this.color = color;
        this.maxLength = maxLength;
    }

    public SetEntity(String note, String specificationFileUrl, String quantity, String color, String maxLength) {
        super(note, specificationFileUrl);
        this.quantity = quantity;
        this.color = color;
        this.maxLength = maxLength;
    }

    public String getQuantity() {

        return quantity;
    }

    public SetEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
