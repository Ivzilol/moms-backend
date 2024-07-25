package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "panels")
public class PanelEntity extends BaseMaterialEntity {

    private String type;
    private String color;
    private String length;
    private String width;
    private String totalThickness;
    private String frontSheetThickness;
    private String backSheetThickness;


    public PanelEntity() {

    }

    public PanelEntity(String type) {
        this.type = type;
    }

    public PanelEntity(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String type) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
    }

    public PanelEntity(Double quantity, String note, String specificationFileUrl, String type) {
        super(quantity, note, specificationFileUrl);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public PanelEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public PanelEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public PanelEntity setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public PanelEntity setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelEntity setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelEntity setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }
}
