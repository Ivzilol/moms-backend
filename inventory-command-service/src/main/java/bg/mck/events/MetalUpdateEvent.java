package bg.mck.events;

import bg.mck.enums.EventType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class MetalUpdateEvent extends BaseEvent {
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
