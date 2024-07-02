package bg.mck.entity.transportEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "transports")
public class TransportEntity {

    @Id
    private String id;
    private String description;
    private double distance;

    public TransportEntity() {
    }

    public TransportEntity(String id, String description, double distance) {
        this.id = id;
        this.description = description;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public TransportEntity setId(String id) {
        this.id = id;
        return this;
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

    public TransportEntity setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportEntity that = (TransportEntity) o;
        return Double.compare(distance, that.distance) == 0 && Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, distance);
    }

    @Override
    public String toString() {
        return "TransportEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                '}';
    }
}
