package bg.mck.ordercommandservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    private String type;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @Column(name = "max_length")
    private String maxLength;
    @Pattern(regexp = "^[^-].*", message = "Area must be positive")
    @Column(name = "area")
    private String area;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Double quantity, String note, String specificationFileUrl, String type, String maxLength, String area) {
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

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(String area) {
        this.area = area;
        return this;
    }
}
