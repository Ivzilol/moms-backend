package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateMetalEvent extends BaseMaterialEvent {
    private String category;
    private String materialType;
    private String name;

    private Double totalWeight;
    public UpdateMetalEvent() {
    }

    public UpdateMetalEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdateMetalEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateMetalEvent setName(String name) {
        this.name = name;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public UpdateMetalEvent setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateMetalEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
