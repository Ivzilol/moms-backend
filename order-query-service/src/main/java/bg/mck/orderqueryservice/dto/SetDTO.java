package bg.mck.orderqueryservice.dto;

public class SetDTO extends BaseDTO{

    private Double galvanisedSheetThickness;

    private String color;

    private String maxLength;

    public SetDTO() {
    }

    public SetDTO(Double galvanisedSheetThickness, String color, String maxLength) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetDTO setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
