package bg.mck.ordercommandservice.dto;

public class ServiceDTO extends BaseDTO {
    private String quantity;

    public ServiceDTO() {
    }

    public ServiceDTO(String quantity) {
        this.quantity = quantity;
    }

    public ServiceDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public ServiceDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
