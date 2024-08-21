package bg.mck.ordercommandservice.dto;


import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GalvanisedSheetDTO extends BaseDTO {

    @NotNull(message = "Type is required")
    private String type;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Area must be positive")
    private String numberOfSheets;

    @NotNull(message = "Quantity is required")
    private String quantity;
    @NotNull(message = "Quantity unit is required")
    private AreaUnits quantityUnit;


    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(String type, String maxLength, LengthUnits maxLengthUnit, String numberOfSheets, String quantity, AreaUnits quantityUnit) {
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public GalvanisedSheetDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String maxLength, LengthUnits maxLengthUnit, String numberOfSheets, String quantity, AreaUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public @NotNull String getType() {
        return type;
    }

    public GalvanisedSheetDTO setType(@NotNull String type) {
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

    public GalvanisedSheetDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetDTO setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public GalvanisedSheetDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public AreaUnits getQuantityUnit() {
        return quantityUnit;
    }

    public GalvanisedSheetDTO setQuantityUnit(AreaUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }
}
