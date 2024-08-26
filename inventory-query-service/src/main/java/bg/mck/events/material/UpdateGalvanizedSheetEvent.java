package bg.mck.events.material;

import bg.mck.enums.AreaUnits;
import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class UpdateGalvanizedSheetEvent extends BaseMaterialEvent {
    private String category;

    private String name;

    private String type;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String numberOfSheets;

    private String description;

    private String specificationFileUrl;

    public UpdateGalvanizedSheetEvent() {
    }

    public UpdateGalvanizedSheetEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdateGalvanizedSheetEvent(Long materialId, EventType eventType, String category, String name, String type, String maxLength, LengthUnits maxLengthUnit, String numberOfSheets, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.numberOfSheets = numberOfSheets;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getCategory() {
        return category;
    }

    public UpdateGalvanizedSheetEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateGalvanizedSheetEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateGalvanizedSheetEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public UpdateGalvanizedSheetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public UpdateGalvanizedSheetEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public UpdateGalvanizedSheetEvent setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateGalvanizedSheetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateGalvanizedSheetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
