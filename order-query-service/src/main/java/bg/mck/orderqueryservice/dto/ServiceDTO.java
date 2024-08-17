package bg.mck.orderqueryservice.dto;

public class ServiceDTO extends BaseDTO {
    private String quantity;

    public ServiceDTO() {
    }

    public ServiceDTO(String id,String quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
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
