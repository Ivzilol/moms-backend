package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class RegisterFastenerEvent extends BaseMaterialEvent {

    private String category;
    private String name;
    private String description;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String type;
    private String specificationFileUrl;


    public RegisterFastenerEvent() {

    }


    public RegisterFastenerEvent(Long materialId, EventType eventType, String category, String name, String description, String diameter, String length, LengthUnits lengthUnit, String model, String clazz, String type, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.standard = model;
        this.clazz = clazz;
        this.type = type;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getCategory() {
        return category;
    }

    public RegisterFastenerEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterFastenerEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RegisterFastenerEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public RegisterFastenerEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public RegisterFastenerEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public RegisterFastenerEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public RegisterFastenerEvent setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public RegisterFastenerEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getType() {
        return type;
    }

    public RegisterFastenerEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RegisterFastenerEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
