package bg.mck.ordercommandservice.dto.Material;

public class PanelDTO {
    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double sheetThickness;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public PanelDTO() {
    }

    public PanelDTO(String type, String color, Double length, Double width, Double totalThickness, Double sheetThickness, Double quantity, String note, String specificationFileUrl) {
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

    public Double getSheetThickness() {
        return sheetThickness;
    }

    public PanelDTO setSheetThickness(Double sheetThickness) {
        this.sheetThickness = sheetThickness;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public PanelDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
