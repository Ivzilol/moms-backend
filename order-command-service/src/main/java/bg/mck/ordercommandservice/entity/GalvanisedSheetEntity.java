package bg.mck.ordercommandservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    private String type;
    @DecimalMin(value = "0.0", inclusive = false, message = "maxLength must be greater than 0")
    @Column(name = "max_length_in_centimeters")
    private Double maxLength;
    @DecimalMin(value = "0.0", inclusive = false, message = "area must be greater than 0")
    @Column(name = "area_in_square_meters")
    private Double area;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Double quantity, String note, String specificationFileUrl, String type, Double maxLength, Double area) {
        super(quantity, note, specificationFileUrl);
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(Double area) {
        this.area = area;
        return this;
    }
}
