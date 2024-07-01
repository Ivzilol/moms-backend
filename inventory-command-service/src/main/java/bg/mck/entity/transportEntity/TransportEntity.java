package bg.mck.entity.transportEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "transports")
public class TransportEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private double distance;

    public TransportEntity() {
    }

    public TransportEntity(Long id, String description, double distance) {
        this.id = id;
        this.description = description;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportEntity that = (TransportEntity) o;
        return Double.compare(that.distance, distance) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, distance);
    }

    @Override
    public String toString() {
        return "TransportRepository{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                '}';
    }
}
