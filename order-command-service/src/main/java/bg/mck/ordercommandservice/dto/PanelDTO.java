package bg.mck.ordercommandservice.dto;

import jakarta.validation.constraints.DecimalMin;

public class PanelDTO extends BaseDTO{

    private String type;
    private String color;

    @DecimalMin(value = "0.0", message = "Length must be positive")
    private Double length;
    @DecimalMin(value = "0.0", message = "Width must be positive")
    private Double width;
    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    private Double totalThickness;
    @DecimalMin(value = "0.0", message = "FrontSheetThickness must be positive")
    private Double FrontSheetThickness;
    @DecimalMin(value = "0.0", message = "BackSheetThickness must be positive")
    private Double BackSheetThickness;


    public PanelDTO() {
    }

    public PanelDTO(String type, String color, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness) {
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        FrontSheetThickness = frontSheetThickness;
        BackSheetThickness = backSheetThickness;
    }

    public String getType() {
        return type;
    }

    public PanelDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public PanelDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelDTO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelDTO setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public PanelDTO setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public PanelDTO setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
        return this;
    }
}
