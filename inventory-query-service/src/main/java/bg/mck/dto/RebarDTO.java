package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;

public class RebarDTO implements MaterialDTO{

    private String id;

    private String name;

    private String maxLength;
    private LengthUnits maxLengthUnit;

    private String description;

    private String specificationFileUrl;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
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

    public RebarDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }
}
