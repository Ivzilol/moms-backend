package bg.mck.ordercommandservice.dto.Material;


import jakarta.validation.constraints.DecimalMin;

public class InsulationDTO extends BaseDTO{

    private String type;

    @DecimalMin(value = "0.0", message = "Thickness must be greater than 0")
    private Double thickness;


    public InsulationDTO() {
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
