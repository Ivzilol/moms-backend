package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    @Column(nullable = false)
    private String type;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @Column(name = "max_length")
    private String maxLength;
    @Pattern(regexp = "^[^-].*", message = "Area must be positive")
    @Column(name = "number_of_sheets")
    private String numberOfSheets;
    @Column(nullable = false)
    private String quantity;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String type, String maxLength, String numberOfSheets, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.maxLength = maxLength;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity;
    }

    public GalvanisedSheetEntity(String note, String specificationFileUrl, String type, String maxLength, String numberOfSheets, String quantity) {
        super(note, specificationFileUrl);
        this.type = type;
        this.maxLength = maxLength;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Area must be positive") String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetEntity setNumberOfSheets(@Pattern(regexp = "^[^-].*", message = "Area must be positive") String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
