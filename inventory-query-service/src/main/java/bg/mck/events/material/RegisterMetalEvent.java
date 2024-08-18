package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.WeightUnits;

public class RegisterMetalEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private String totalWeight;
    private WeightUnits totalWeightUnit;

    private String kind;

    private String description;

    private String specificationFileUrl;

    public RegisterMetalEvent(Long materialId, EventType eventType, String category, String name, String totalWeight, WeightUnits totalWeightUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterMetalEvent() {

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

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
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

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public RegisterMetalEvent setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public RegisterMetalEvent setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
