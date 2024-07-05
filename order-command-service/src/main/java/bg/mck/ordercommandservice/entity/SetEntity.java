package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "sets")
public class SetEntity extends BaseMaterialEntity {

    @DecimalMin(value = "0.0", message = "GalvanisedSheetThickness must be positive")
    @Column(name = "galvanised_sheet_thickness_in_mm")
    private Double galvanisedSheetThickness;

    private String Color;

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private String maxLength;

    public SetEntity() {
    }

    public Double getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetEntity setGalvanisedSheetThickness(Double galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return Color;
    }

    public SetEntity setColor(String color) {
        Color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
