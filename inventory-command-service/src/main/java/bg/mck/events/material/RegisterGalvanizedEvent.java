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

    private String numberOfSheets;

    private String description;

    private String specificationFileUrl;

    public RegisterGalvanizedEvent() {

    }


    public RegisterGalvanizedEvent(Long materialId, EventType eventType, String category, String name, String type, String maxLength, LengthUnits maxLengthUnit, String numberOfSheets, String description, String specificationFileUrl) {
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

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public RegisterGalvanizedEvent setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
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
