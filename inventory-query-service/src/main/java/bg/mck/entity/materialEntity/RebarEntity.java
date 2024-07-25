package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rebars")
public class RebarEntity extends BaseMaterialEntity{

    @Indexed
    private String name;

    private Double maxLength;

    private Double weight;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RebarEntity() {
    }

    public RebarEntity(String name, Double maxLength, Double weight, Double quantity, String description, String specificationFileUrl) {
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RebarEntity(String id, String name, Double maxLength, Double weight, Double quantity, String description, String specificationFileUrl) {
        super(id);
        this.name = name;
        this.maxLength = maxLength;
        this.weight = weight;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
