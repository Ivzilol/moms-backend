package bg.mck.ordercommandservice.entity.transport;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transports")
public class TransportEntity extends BaseEntity {
    private String description;
    private double distance;

    public TransportEntity() {
    }

    public TransportEntity(String description, double distance) {
        this.description = description;
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public TransportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getDistance() {
        return distance;
    }

    public TransportEntity setDistance(double km) {
        this.distance = km;
        return this;
    }
}
