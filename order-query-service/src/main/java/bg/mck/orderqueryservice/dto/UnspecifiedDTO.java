package bg.mck.orderqueryservice.dto;


public class UnspecifiedDTO extends BaseDTO {
    private String quantity;

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(String quantity) {
        this.quantity = quantity;
    }

    public UnspecifiedDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public UnspecifiedDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
