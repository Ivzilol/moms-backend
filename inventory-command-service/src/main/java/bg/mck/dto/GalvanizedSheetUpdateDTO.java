package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GalvanizedSheetUpdateDTO {
    private String name;

    @NotNull(message = "Type is required")
    private String type;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull
    private String maxLength;

    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Number of sheets must be positive")
    private String numberOfSheets;

    private String description;

    private String specificationFileUrl;

    public GalvanizedSheetUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public GalvanizedSheetUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanizedSheetUpdateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanizedSheetUpdateDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanizedSheetUpdateDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanizedSheetUpdateDTO setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public GalvanizedSheetUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanizedSheetUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
