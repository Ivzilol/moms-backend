package bg.mck.orderqueryservice.events;

public class SetEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String galvanisedSheetThickness;
    private String color;
    private String maxLength;

    public SetEvent() {
    }

    public SetEvent(Long id, Double quantity, String description, String specificationFileUrl, String galvanisedSheetThickness, String color, String maxLength) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.color = color;
        this.maxLength = maxLength;
    }

    public Long getId() {
        return id;
    }

    public SetEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public SetEvent setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public String getColor() {
        return color;
    }

    public SetEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
