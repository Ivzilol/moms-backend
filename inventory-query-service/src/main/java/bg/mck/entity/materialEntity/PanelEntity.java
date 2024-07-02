package bg.mck.entity.materialEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "panels")
public class PanelEntity {

    @Id
    private String id;
    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double sheetThickness;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public PanelEntity() {
    }

    public PanelEntity(String id, String type, String color, Double length, Double width, Double totalThickness, Double sheetThickness, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        this.sheetThickness = sheetThickness;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public PanelEntity setId(String id) {
        this.id = id;
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

    public Double getLength() {
        return length;
    }

    public PanelEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelEntity setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelEntity setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public PanelEntity setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public PanelEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(color, that.color) && Objects.equals(length, that.length) && Objects.equals(width, that.width) && Objects.equals(totalThickness, that.totalThickness) && Objects.equals(sheetThickness, that.sheetThickness) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, color, length, width, totalThickness, sheetThickness, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "PanelEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", totalThickness=" + totalThickness +
                ", sheetThickness=" + sheetThickness +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
