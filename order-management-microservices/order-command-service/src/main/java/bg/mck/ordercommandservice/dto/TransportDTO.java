package bg.mck.ordercommandservice.dto;

public class TransportDTO {
    private Long id;
    private String description;
    private Double distance;

    public TransportDTO() {
    }

    public TransportDTO(Long id, String description, Double distance) {
        this.id = id;
        this.description = description;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public TransportDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransportDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public TransportDTO setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
