package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("panels")
public class PanelEntity extends BaseMaterialEntity {

    private String type;
    private String color;
    private String length;
    private String width;
    private String totalThickness;
    private String frontSheetThickness;
    private String backSheetThickness;
    private String quantity;


    public PanelEntity() {

    }

    public PanelEntity(String type) {
        this.type = type;
    }

    public PanelEntity(String id, String quantity, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String type) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.quantity = quantity;
    }

    public PanelEntity(String quantity, String note, String specificationFileUrl, String type) {
        super(note, specificationFileUrl);
        this.type = type;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public PanelEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(color, that.color) &&
                Objects.equals(length, that.length) &&
                Objects.equals(width, that.width) &&
                Objects.equals(totalThickness, that.totalThickness) &&
                Objects.equals(frontSheetThickness, that.frontSheetThickness) &&
                Objects.equals(backSheetThickness, that.backSheetThickness) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, color, length, width, totalThickness, frontSheetThickness, backSheetThickness, quantity);
    }

    @Override
    public String toString() {
        return "PanelEntity{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", totalThickness='" + totalThickness + '\'' +
                ", frontSheetThickness='" + frontSheetThickness + '\'' +
                ", backSheetThickness='" + backSheetThickness + '\'' +
                ", quantity='" + quantity + '\'' +
                "} " + super.toString();
    }
}
