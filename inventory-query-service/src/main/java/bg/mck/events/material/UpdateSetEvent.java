package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class UpdateSetEvent extends BaseMaterialEvent {

    public UpdateSetEvent() {
    }

    public UpdateSetEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdateSetEvent(Long materialId, EventType eventType, String category, String name, String materialType, String color, String maxLength, LengthUnits maxLengthUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.materialType = materialType;
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    private String category;
    private String name;
    private String materialType;
    private String color;
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String description;
    private String specificationFileUrl;

    public String getCategory() {
        return category;
    }

    public UpdateSetEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateSetEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdateSetEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }


    public String getColor() {
        return color;
    }

    public UpdateSetEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public UpdateSetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public UpdateSetEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateSetEvent setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateSetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
