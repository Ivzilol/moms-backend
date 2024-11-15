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


    public RegisterFastenerEvent(Long materialId, EventType eventType, String category, String name, String description, String diameter, String length, LengthUnits lengthUnit, String standard, String clazz, String type, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.standard = standard;
        this.clazz = clazz;
        this.type = type;
        this.specificationFileUrl = specificationFileUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public RegisterFastenerEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }
}
