package bg.mck.entity.materialEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "insulation")
public class InsulationEntity {

    private String id;

    private String name;

    private String type;
    private Double thickness;
    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public InsulationEntity() {
    }

    public InsulationEntity(String id, String name, String type, Double thickness, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.thickness = thickness;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
