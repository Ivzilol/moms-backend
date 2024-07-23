package bg.mck.orderqueryservice.events;


public class GalvanisedSheetEvent extends BaseEvent{
    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;

    private String type;
    private Double Maxlength;
    private Double area;

    public GalvanisedSheetEvent() {
    }

    public GalvanisedSheetEvent(Long id, Double quantity, String description, String specificationFileUrl, String type, Double maxlength, Double area) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        Maxlength = maxlength;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public GalvanisedSheetEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalvanisedSheetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanisedSheetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEvent setType(String type) {
        this.type = type;
        return this;
    }

    public Double getMaxlength() {
        return Maxlength;
    }

    public GalvanisedSheetEvent setMaxlength(Double maxlength) {
        Maxlength = maxlength;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetEvent setArea(Double area) {
        this.area = area;
        return this;
    }
}
