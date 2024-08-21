package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document("galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    private String type;

    @Field(name = "max_length")
    private String maxLength;

    @Field(name = "number_of_sheets")
    private String numberOfSheets;
    private String quantity;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(String type, String maxLength, String numberOfSheets, String quantity) {
        this.type = type;
        this.maxLength = maxLength;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity;
    }

    public GalvanisedSheetEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String type, String maxLength, String numberOfSheets, String quantity) {
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
        if (!super.equals(o)) return false;
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(maxLength, that.maxLength) &&
                Objects.equals(numberOfSheets, that.numberOfSheets) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, maxLength, numberOfSheets, quantity);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "type='" + type + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", numberOfSheets='" + numberOfSheets + '\'' +
                ", quantity='" + quantity + '\'' +
                "} " + super.toString();
    }
}
