package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sets")
public class SetEntity extends BaseMaterialEntity {

    private String galvanisedSheetThickness;
    private String color;
    private String maxLength;

    public SetEntity() {
    }

    public SetEntity(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String galvanisedSheetThickness, String color, String maxLength) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetEntity setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetEntity setColor(String color) {
        this.color = color;
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
