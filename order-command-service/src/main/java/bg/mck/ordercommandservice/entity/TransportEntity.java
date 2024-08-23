package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "transports")
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

    public TransportEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String maxLength, String weight, String truck, String quantity) {
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
    public String toString() {
        return "TransportEntity{" +
                "maxLength='" + maxLength + '\'' +
                ", weight='" + weight + '\'' +
                ", truck='" + truck + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
