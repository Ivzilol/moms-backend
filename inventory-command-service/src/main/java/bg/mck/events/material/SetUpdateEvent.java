package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class SetUpdateEvent extends BaseMaterialEvent {

    public SetUpdateEvent() {
    }

    public SetUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String materialType;
    private String category;

    private String name;


    private String color;

    private String maxLength;
    private LengthUnits maxLengthUnit;


    private String description;

    private String specificationFileUrl;

    public String getMaterialType() {
        return materialType;
    }

    public SetUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SetUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public SetUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }


    public String getColor() {
        return color;
    }

    public SetUpdateEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetUpdateEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetUpdateEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public SetUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
