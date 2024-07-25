package bg.mck.ordercommandservice.event;

public class FasterEvent {

    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String diameter;
    private String length;
    private String model;
    private String clazz;

    public FasterEvent() {
    }

    public Long getId() {
        return id;
    }

    public FasterEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public FasterEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FasterEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FasterEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public FasterEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FasterEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FasterEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FasterEvent setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FasterEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }
}
