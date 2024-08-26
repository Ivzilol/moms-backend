package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;

public class UpdateRebarEvent extends BaseMaterialEvent {

    public UpdateRebarEvent() {
    }

    public UpdateRebarEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdateRebarEvent(Long materialId, EventType eventType, String category, String name, String materialType, String maxLength, LengthUnits maxLengthUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.materialType = materialType;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    private String category;

    private String name;
    private String materialType;
    private String maxLength;
    private LengthUnits maxLengthUnit;

    private String description;

    private String specificationFileUrl;

    public String getCategory() {
        return category;
    }

    public UpdateRebarEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateRebarEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdateRebarEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public UpdateRebarEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public UpdateRebarEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public UpdateRebarEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateRebarEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
