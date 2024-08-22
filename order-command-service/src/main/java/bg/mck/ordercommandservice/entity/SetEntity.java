package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "sets")
public class SetEntity extends BaseMaterialEntity {

    @Column(nullable = false)
    private String quantity;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String maxLength;

    public SetEntity() {
    }

    public SetEntity(String quantity, String color, String maxLength) {
        this.quantity = quantity;
        this.color = color;
        this.maxLength = maxLength;
    }

    public SetEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String quantity, String color, String maxLength) {
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

    public String getQuantity() {
        return quantity;
    }

    public SetEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(quantity, setEntity.quantity) && Objects.equals(color, setEntity.color) && Objects.equals(maxLength, setEntity.maxLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, color, maxLength);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "quantity='" + quantity + '\'' +
                ", color='" + color + '\'' +
                ", maxLength='" + maxLength + '\'' +
                '}';
    }
}
