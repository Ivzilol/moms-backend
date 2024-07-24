package bg.mck.ordercommandservice.dto;


import jakarta.validation.constraints.DecimalMin;
import org.springframework.web.multipart.MultipartFile;

public class InsulationDTO extends BaseDTO{

    private String type;

    @DecimalMin(value = "0.0", message = "Thickness must be greater than 0")
    private Double thickness;


    public InsulationDTO() {
    }

    public InsulationDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, Double thickness) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.thickness = thickness;
    }

    public InsulationDTO(String type, Double thickness) {
        this.type = type;
        this.thickness = thickness;
    }

    public String getType() {
        return type;
    }

    public InsulationDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationDTO setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }
}
