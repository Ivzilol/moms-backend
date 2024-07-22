package bg.mck.orderqueryservice.events;

public class InsulationEvent extends BaseEvent{

    private Long id;

    private Double quantity;
    private String description;
    private String specificationFileUrl;

    private String type;

    private Double thickness;

    public InsulationEvent() {
    }

    public InsulationEvent(Long id, Double quantity, String description, String specificationFileUrl, String type, Double thickness) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.thickness = thickness;
    }

    public Long getId() {
        return id;
    }

    public InsulationEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationEvent setType(String type) {
        this.type = type;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationEvent setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }
}
