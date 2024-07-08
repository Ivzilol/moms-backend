package bg.mck.entity.materialEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "")
public class SetEntity {

    private String id;

    private String name;

    @DecimalMin(value = "0.0", message = "GalvanisedSheetThickness must be positive")
    @Column(name = "galvanised_sheet_thickness_in_mm")
    private Double galvanisedSheetThickness;

    private String color;

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private String maxLength;

    public SetEntity() {
    }

    public SetEntity(String id, String name, Double galvanisedSheetThickness, String color, String maxLength) {
        this.id = id;
        this.name = name;
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public void setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
}
