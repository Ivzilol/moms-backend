package bg.mck.ordercommandservice.dto;


import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.Pattern;

public class GalvanisedSheetDTO extends BaseDTO {

    private String type;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Area must be positive")
    private String area;
    private AreaUnits areaUnit;


    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String maxLength, LengthUnits maxLengthUnit, String area, AreaUnits areaUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.area = area;
        this.areaUnit = areaUnit;
    }

    public GalvanisedSheetDTO(String type, String maxLength, String area) {
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanisedSheetDTO setMaxLengthUnit(LengthUnits lengthUnit) {
        this.maxLengthUnit = lengthUnit;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanisedSheetDTO setArea(String area) {
        this.area = area;
        return this;
    }

    public AreaUnits getAreaUnit() {
        return areaUnit;
    }

    public GalvanisedSheetDTO setAreaUnit(AreaUnits areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }
}
