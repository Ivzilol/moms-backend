package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("rebars")
public class RebarEntity extends BaseMaterialEntity {

    private String maxLength;
    private String quantity;

    public RebarEntity() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RebarEntity that = (RebarEntity) o;
        return Objects.equals(maxLength, that.maxLength) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxLength, quantity);
    }

    @Override
    public String toString() {
        return "RebarEntity{" +
                "maxLength='" + maxLength + '\'' +
                ", quantity='" + quantity + '\'' +
                "} " + super.toString();
    }
}
