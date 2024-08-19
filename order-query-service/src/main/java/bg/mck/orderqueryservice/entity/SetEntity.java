package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(quantity, setEntity.quantity) &&
                Objects.equals(color, setEntity.color) &&
                Objects.equals(maxLength, setEntity.maxLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, color, maxLength);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "quantity='" + quantity + '\'' +
                ", color='" + color + '\'' +
                ", maxLength='" + maxLength + '\'' +
                "} " + super.toString();
    }
}
