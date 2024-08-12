package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.WeightUnits;

public class MetalUpdateEvent extends BaseMaterialEvent {
    private String materialType;
    private String category;
    private String name;

    private String totalWeight;
    private WeightUnits totalWeightUnit;

    private Double quantity;

    private String description;

    private String specificationFileUrl;


    public MetalUpdateEvent() {
    }

    public MetalUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public String getMaterialType() {
        return materialType;
    }

    public MetalUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public MetalUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public MetalUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalUpdateEvent setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalUpdateEvent setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
