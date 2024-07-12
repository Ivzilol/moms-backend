package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "panels")
public class PanelEntity extends BaseMaterialEntity {

    private String type;
    private String color;

    @DecimalMin(value = "0.0", message = "Length must be positive")
    @Column(name = "length_in_centimeters")
    private Double length;

    @DecimalMin(value = "0.0", message = "Width must be positive")
    @Column(name = "width_in_centimeters")
    private Double width;

    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    @Column(name = "total_thickness_in_mm")
    private Double totalThickness;

    @DecimalMin(value = "0.0", message = "FrontSheetThickness must be positive")
    @Column(name = "front_sheet_thickness_in_mm")
    private Double frontSheetThickness;

    @DecimalMin(value = "0.0", message = "BackSheetThickness must be positive")
    @Column(name = "back_sheet_thickness_in_mm")
    private Double backSheetThickness;


    public PanelEntity() {

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

    public Double getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelEntity setFrontSheetThickness(Double frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelEntity setBackSheetThickness(Double backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }
}
