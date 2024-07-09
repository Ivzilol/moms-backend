package bg.mck.ordercommandservice.event;

public class PanelEvent {
    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;

    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double FrontSheetThickness;
    private Double BackSheetThickness;

    public PanelEvent() {
    }

    public PanelEvent(Long id, Double quantity, String description, String specificationFileUrl, String type, String color, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        FrontSheetThickness = frontSheetThickness;
        BackSheetThickness = backSheetThickness;
    }

    public Long getId() {
        return id;
    }

    public PanelEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PanelEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public PanelEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public PanelEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelEvent setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelEvent setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public PanelEvent setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public PanelEvent setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
        return this;
    }
}

