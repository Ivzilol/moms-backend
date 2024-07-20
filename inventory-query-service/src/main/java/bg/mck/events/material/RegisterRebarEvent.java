package bg.mck.events.material;

import bg.mck.enums.EventType;

public class RegisterRebarEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private Double MaxLength;

    private Double weight;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RegisterRebarEvent(Long materialId, EventType eventType, String category, String name, Double maxLength, Double weight, Double quantity, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        MaxLength = maxLength;
        this.weight = weight;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterRebarEvent() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxLength() {
        return MaxLength;
    }

    public void setMaxLength(Double maxLength) {
        MaxLength = maxLength;
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
