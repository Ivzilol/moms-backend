package bg.mck.events.material;

import bg.mck.enums.AreaUnits;
import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class GalvanizedSheetUpdateEvent extends BaseMaterialEvent {

    private String materialType;
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

    public GalvanizedSheetUpdateEvent() {
    }

    public GalvanizedSheetUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }


    public String getCategory() {
        return category;
    }

    public GalvanizedSheetUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public GalvanizedSheetUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanizedSheetUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanizedSheetUpdateEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanizedSheetUpdateEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanizedSheetUpdateEvent setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public GalvanizedSheetUpdateEvent setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanizedSheetUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalvanizedSheetUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanizedSheetUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public GalvanizedSheetUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }
}
