package bg.mck.ordercommandservice.dto.Material;


import jakarta.validation.constraints.DecimalMin;

public class GalvanisedSheetDTO extends BaseDTO{

    private String type;

    @DecimalMin(value = "0.0", message = "Length must be greater than 0")
    private Double Maxlength;

    @DecimalMin(value = "0.0", message = "Area must be greater than 0")
    private Double area;


    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(String type, Double maxlength, Double area) {
        this.type = type;
        Maxlength = maxlength;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Double getMaxlength() {
        return Maxlength;
    }

    public GalvanisedSheetDTO setMaxlength(Double maxlength) {
        Maxlength = maxlength;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetDTO setArea(Double area) {
        this.area = area;
        return this;
    }
}
