package bg.mck.ordercommandservice.dto;

public class ServiceDTO extends BaseDTO {

    public ServiceDTO() {
    }

    public ServiceDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
    }
}
