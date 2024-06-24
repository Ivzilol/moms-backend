package bg.mck.ordercommandservice.entity.transport;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transports")
public class TransportEntity  extends BaseEntity {
    private String description;
    private double km;

    public TransportEntity() {
    }

    public TransportEntity(String description, double km) {
        this.description = description;
        this.km = km;
    }

    public String getDescription() {
        return description;
    }

    public TransportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getKm() {
        return km;
    }

    public TransportEntity setKm(double km) {
        this.km = km;
        return this;
    }
}
