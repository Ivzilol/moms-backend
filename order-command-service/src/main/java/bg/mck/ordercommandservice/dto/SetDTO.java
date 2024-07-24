package bg.mck.ordercommandservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.web.multipart.MultipartFile;

public class SetDTO extends BaseDTO {

    @DecimalMin(value = "0.0", message = "GalvanisedSheetThickness must be positive")
    @Column(name = "galvanised_sheet_thickness_in_mm")
    private Double galvanisedSheetThickness;

    private String color;

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private String maxLength;

    public SetDTO() {
    }

    public SetDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, Double galvanisedSheetThickness, String color, String maxLength) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
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
