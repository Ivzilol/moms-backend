package bg.mck.ordercommandservice.event;


public class GalvanisedSheetEvent {
    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;

    private String type;
    private String maxLength;
    private String area;

    public GalvanisedSheetEvent() {
    }

    public GalvanisedSheetEvent(Long id, Double quantity, String description, String specificationFileUrl, String type, String maxLength, String area) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.maxLength = maxLength;
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

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanisedSheetEvent setArea(String area) {
        this.area = area;
        return this;
    }
}
