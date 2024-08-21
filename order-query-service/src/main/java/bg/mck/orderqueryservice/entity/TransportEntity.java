package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("transports")
public class TransportEntity extends BaseMaterialEntity {

    private String maxLength;
    private String weight;
    private String truck;
    private String quantity;

    public TransportEntity() {
    }

    public TransportEntity(String maxLength, String weight, String truck, String quantity) {
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
        this.quantity = quantity;
    }

    public TransportEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String maxLength, String weight, String truck, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
        this.quantity = quantity;
    }

    public TransportEntity(String note, String specificationFileUrl, String maxLength, String weight, String truck, String quantity) {
        super(note, specificationFileUrl);
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
        this.quantity = quantity;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public TransportEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public TransportEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public TransportEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportEntity setTruck(String truck) {
        this.truck = truck;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TransportEntity that = (TransportEntity) o;
        return Objects.equals(maxLength, that.maxLength) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(truck, that.truck) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxLength, weight, truck, quantity);
    }

    @Override
    public String toString() {
        return "TransportEntity{" +
                "maxLength='" + maxLength + '\'' +
                ", weight='" + weight + '\'' +
                ", truck='" + truck + '\'' +
                ", quantity='" + quantity + '\'' +
                "} " + super.toString();
    }
}
