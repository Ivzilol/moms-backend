package bg.mck.ordercommandservice.dto;


import jakarta.validation.constraints.DecimalMin;

public class GalvanisedSheetDTO extends BaseDTO{

    private String type;

    @DecimalMin(value = "0.0", message = "Length must be greater than 0")
    private Double maxlength;

    @DecimalMin(value = "0.0", message = "Area must be greater than 0")
    private Double area;


    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, Double maxlength, Double area) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.maxlength = maxlength;
        this.area = area;
    }

    public GalvanisedSheetDTO(String type, Double maxlength, Double area) {
        this.type = type;
        this.maxlength = maxlength;
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
        return maxlength;
    }

    public GalvanisedSheetDTO setMaxlength(Double maxlength) {
        this.maxlength = maxlength;
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
