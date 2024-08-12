package bg.mck.events.material;

import bg.mck.enums.AreaUnits;
import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class RegisterGalvanizedEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private String type;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String area;

    private AreaUnits areaUnit;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RegisterGalvanizedEvent() {

    }

    public RegisterGalvanizedEvent(Long materialId, EventType eventType, String category, String name, String type, String maxLength, LengthUnits maxLengthUnit, String area, AreaUnits areaUnit, Double quantity, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.area = area;
        this.areaUnit = areaUnit;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getCategory() {
        return category;
    }

    public RegisterGalvanizedEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterGalvanizedEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RegisterGalvanizedEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RegisterGalvanizedEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RegisterGalvanizedEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getArea() {
        return area;
    }

    public RegisterGalvanizedEvent setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public RegisterGalvanizedEvent setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RegisterGalvanizedEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RegisterGalvanizedEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RegisterGalvanizedEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
