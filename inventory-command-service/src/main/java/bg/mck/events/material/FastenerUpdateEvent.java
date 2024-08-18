package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class FastenerUpdateEvent extends BaseMaterialEvent {

    private String category;
    private String materialType;
    private String name;
    private String description;
    private String diameter;
    private String length;
    private LengthUnits lengthUnit;
    private String standard;
    private String clazz;
    private String note;
    private String specificationFileUrl;

    public FastenerUpdateEvent() {
    }


    public FastenerUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public String getCategory() {
        return category;
    }

    public FastenerUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public FastenerUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getName() {
        return name;
    }

    public FastenerUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FastenerUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerUpdateEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FastenerUpdateEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public FastenerUpdateEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FastenerUpdateEvent setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerUpdateEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }


    public String getNote() {
        return note;
    }

    public FastenerUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
