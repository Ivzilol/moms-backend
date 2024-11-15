package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class UpdateFastenerEvent extends BaseMaterialEvent {

    private String name;
    private String description;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String type;
    private String specificationFileUrl;
    private String category;

    public UpdateFastenerEvent() {
    }

    public UpdateFastenerEvent(Long materialId, EventType eventType, String name, String description, String diameter, String length, LengthUnits lengthUnit, String standard, String clazz, String type, String specificationFileUrl, String category) {
        super(materialId, eventType);
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.standard = standard;
        this.clazz = clazz;
        this.type = type;
        this.specificationFileUrl = specificationFileUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public UpdateFastenerEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateFastenerEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public UpdateFastenerEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public UpdateFastenerEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public UpdateFastenerEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public UpdateFastenerEvent setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UpdateFastenerEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getType() {
        return type;
    }

    public UpdateFastenerEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateFastenerEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateFastenerEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
