package bg.mck.ordercommandservice.dto.errorDTO;

public class OrderCreationErrorsDTO {

        private String orderDescriptionError;

        private String orderDateError;

        private String deliveryDateError;

        private ConstructionSiteErrorDTO constructionSiteError;

        private MaterialErrorDTO materialError;

        private ServiceErrorDTO serviceError;

        private TransportErrorDTO transportError;

    public OrderCreationErrorsDTO() {
    }

}
