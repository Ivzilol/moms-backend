package bg.mck.orderqueryservice.dto;

public class ServiceDTO extends BaseDTO {

    public ServiceDTO() {
    }

    public ServiceDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
    }
}
