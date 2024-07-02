package bg.mck.ordercommandservice.dto.errorDTO;

import org.springframework.validation.BindingResult;

import java.util.Map;

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

    public OrderCreationErrorsDTO(String orderDescriptionError, String orderDateError, String deliveryDateError, ConstructionSiteErrorDTO constructionSiteError, MaterialErrorDTO materialError, ServiceErrorDTO serviceError, TransportErrorDTO transportError) {
        this.orderDescriptionError = orderDescriptionError;
        this.orderDateError = orderDateError;
        this.deliveryDateError = deliveryDateError;
        this.constructionSiteError = constructionSiteError;
        this.materialError = materialError;
        this.serviceError = serviceError;
        this.transportError = transportError;
    }

    public String getOrderDescriptionError() {
        return orderDescriptionError;
    }

    public OrderCreationErrorsDTO setOrderDescriptionError(String orderDescriptionError) {
        this.orderDescriptionError = orderDescriptionError;
        return this;
    }

    public String getOrderDateError() {
        return orderDateError;
    }

    public OrderCreationErrorsDTO setOrderDateError(String orderDateError) {
        this.orderDateError = orderDateError;
        return this;
    }

    public String getDeliveryDateError() {
        return deliveryDateError;
    }

    public OrderCreationErrorsDTO setDeliveryDateError(String deliveryDateError) {
        this.deliveryDateError = deliveryDateError;
        return this;
    }

    public ConstructionSiteErrorDTO getConstructionSiteError() {
        return constructionSiteError;
    }

    public OrderCreationErrorsDTO setConstructionSiteError(ConstructionSiteErrorDTO constructionSiteError) {
        this.constructionSiteError = constructionSiteError;
        return this;
    }

    public MaterialErrorDTO getMaterialError() {
        return materialError;
    }

    public OrderCreationErrorsDTO setMaterialError(MaterialErrorDTO materialError) {
        this.materialError = materialError;
        return this;
    }

    public ServiceErrorDTO getServiceError() {
        return serviceError;
    }

    public OrderCreationErrorsDTO setServiceError(ServiceErrorDTO serviceError) {
        this.serviceError = serviceError;
        return this;
    }

    public TransportErrorDTO getTransportError() {
        return transportError;
    }

    public OrderCreationErrorsDTO setTransportError(TransportErrorDTO transportError) {
        this.transportError = transportError;
        return this;
    }
}
