package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;

public class RebarUpdateEvent extends BaseMaterialEvent {

    public RebarUpdateEvent() {
    }

    public RebarUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String materialType;
    private String category;

    private String name;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String description;

    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public RebarUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RebarUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RebarUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarUpdateEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarUpdateEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public RebarUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
