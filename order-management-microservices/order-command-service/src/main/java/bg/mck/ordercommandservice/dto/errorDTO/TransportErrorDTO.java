package bg.mck.ordercommandservice.dto.errorDTO;

public class TransportErrorDTO {
    private String descriptionError;
    private Double distanceError;

    public TransportErrorDTO() {
    }

    public TransportErrorDTO(String descriptionError, Double distanceError) {
        this.descriptionError = descriptionError;
        this.distanceError = distanceError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public TransportErrorDTO setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
        return this;
    }

    public Double getDistanceError() {
        return distanceError;
    }

    public TransportErrorDTO setDistanceError(Double distanceError) {
        this.distanceError = distanceError;
        return this;
    }
}
