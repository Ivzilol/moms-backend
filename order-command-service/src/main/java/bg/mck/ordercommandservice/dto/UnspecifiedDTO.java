package bg.mck.ordercommandservice.dto;

import org.springframework.web.multipart.MultipartFile;

public class UnspecifiedDTO extends BaseDTO {

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
    }
}
