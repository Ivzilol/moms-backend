package bg.mck.ordercommandservice.dto.errorDTO;

public class TransportErrorDTO {
    private String descriptionError;
    private String distanceError;

    public TransportErrorDTO() {
    }

    public TransportErrorDTO(String descriptionError, String distanceError) {
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

    public String getDistanceError() {
        return distanceError;
    }

    public TransportErrorDTO setDistanceError(String distanceError) {
        this.distanceError = distanceError;
        return this;
    }
}
