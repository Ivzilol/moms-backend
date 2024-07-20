package bg.mck.events.material;

import bg.mck.enums.EventType;

public class MetalUpdateEvent extends BaseMaterialEvent {
    private String category;
    private String materialType;
    private String name;

    private Double totalWeight;
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

    public String getName() {
        return name;
    }

    public MetalUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public MetalUpdateEvent setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public MetalUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
