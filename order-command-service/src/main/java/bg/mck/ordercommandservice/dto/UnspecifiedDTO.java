package bg.mck.ordercommandservice.dto;


public class UnspecifiedDTO extends BaseDTO {

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
    }
}
