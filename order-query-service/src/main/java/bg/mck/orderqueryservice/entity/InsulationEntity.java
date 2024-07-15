package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("insulation")
public class InsulationEntity {

    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String thickness;

    public InsulationEntity() {
    }


    public InsulationEntity(String id, Double quantity, String description, String specificationFileUrl, String type, String thickness) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.thickness = thickness;
    }

    public String getId() {
        return id;
    }

    public InsulationEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }
}
