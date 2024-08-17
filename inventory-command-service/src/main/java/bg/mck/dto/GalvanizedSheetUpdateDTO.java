package bg.mck.dto;

import bg.mck.enums.AreaUnits;
import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.DecimalMin;

public class GalvanizedSheetUpdateDTO {
    private String name;

    private String type;

    private String maxLength;

    private LengthUnits maxLengthUnit;

    private String area;

    private AreaUnits areaUnit;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

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

    public String getArea() {
        return area;
    }

    public GalvanizedSheetUpdateDTO setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public GalvanizedSheetUpdateDTO setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanizedSheetUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
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
