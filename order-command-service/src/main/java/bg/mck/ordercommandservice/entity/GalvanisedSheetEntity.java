package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    @Column(nullable = false)
    private String type;
    private String maxLength;
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

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetEntity setNumberOfSheets(String numberOfSheets) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(type, that.type) && Objects.equals(maxLength, that.maxLength) && Objects.equals(numberOfSheets, that.numberOfSheets) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, maxLength, numberOfSheets, quantity);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "type='" + type + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", numberOfSheets='" + numberOfSheets + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
