package bg.mck.ordercommandservice.dto.errorDTO;

public class ServiceErrorDTO {
    private String descriptionError;
    private String priceError;

    public ServiceErrorDTO() {
    }

    public ServiceErrorDTO(String descriptionError, String priceError) {
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

    public String getPriceError() {
        return priceError;
    }

    public ServiceErrorDTO setPriceError(String priceError) {
        this.priceError = priceError;
        return this;
    }
}
