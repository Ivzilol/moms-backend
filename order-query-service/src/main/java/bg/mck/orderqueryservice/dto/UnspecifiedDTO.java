package bg.mck.orderqueryservice.dto;


public class UnspecifiedDTO extends BaseDTO {

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
    }
}
