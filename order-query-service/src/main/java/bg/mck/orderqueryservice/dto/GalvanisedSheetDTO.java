package bg.mck.orderqueryservice.dto;


public class GalvanisedSheetDTO extends BaseDTO{

    private String type;

    private Double maxlength;

    private Double area;


    public GalvanisedSheetDTO() {
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
