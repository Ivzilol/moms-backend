package bg.mck.dto;

import bg.mck.enums.AreaUnits;
import bg.mck.enums.LengthUnits;

public class GalvanisedDTO implements MaterialDTO{

    private Long id;

    private String name;

    private String type;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String numberOfSheets;

    private String description;

    private String specificationFileUrl;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public void setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }


    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanisedDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }
}
