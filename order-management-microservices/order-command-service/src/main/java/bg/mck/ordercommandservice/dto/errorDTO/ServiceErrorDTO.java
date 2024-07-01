package bg.mck.ordercommandservice.dto.errorDTO;

public class ServiceErrorDTO {
    private String descriptionError;
    private Double priceError;

    public ServiceErrorDTO() {
    }

    public ServiceErrorDTO(String descriptionError, Double priceError) {
        this.descriptionError = descriptionError;
        this.priceError = priceError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public ServiceErrorDTO setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
        return this;
    }

    public Double getPriceError() {
        return priceError;
    }

    public ServiceErrorDTO setPriceError(Double priceError) {
        this.priceError = priceError;
        return this;
    }
}
