package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.WeightUnits;

public class UpdateMetalEvent extends BaseMaterialEvent {
    private String category;
    private String materialType;
    private String name;

    private String totalWeight;
    private WeightUnits totalWeightUnit;

    private String kind;

    private String description;

    private String specificationFileUrl;
    public UpdateMetalEvent() {
    }

    public UpdateMetalEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdateMetalEvent(Long materialId, EventType eventType, String category, String materialType, String name, String totalWeight, WeightUnits totalWeightUnit, String kind, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.materialType = materialType;
        this.name = name;
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
        this.kind = kind;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getCategory() {
        return category;
    }

    public UpdateMetalEvent setCategory(String category) {
        this.category = category;
        return this;
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

    public String getTotalWeight() {
        return totalWeight;
    }

    public UpdateMetalEvent setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public UpdateMetalEvent setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public UpdateMetalEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateMetalEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public UpdateMetalEvent setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
