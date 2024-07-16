package bg.mck.orderqueryservice.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class SetDTO extends BaseDTO {

    @Field(name = "galvanised_sheet_thickness_in_mm")
    private Double galvanisedSheetThickness;

    private String color;

    @Field(name = "max_length_in_centimeters")
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
