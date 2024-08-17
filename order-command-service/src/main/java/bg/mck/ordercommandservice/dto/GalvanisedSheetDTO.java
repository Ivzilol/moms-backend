package bg.mck.ordercommandservice.dto;


import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GalvanisedSheetDTO extends BaseDTO {

    @NotNull
    private String type;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull
    private String maxLength;
    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Area must be positive")
    @NotNull
    private String numberOfSheets;

    private String quantity;
    private AreaUnits quantityUnit;


    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String maxLength, LengthUnits maxLengthUnit, String numberOfSheets, String quantity1, AreaUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.numberOfSheets = numberOfSheets;
        this.quantity = quantity1;
        this.quantityUnit = quantityUnit;
    }

    public @NotNull String getType() {
        return type;
    }

    public GalvanisedSheetDTO setType(@NotNull String type) {
        this.type = type;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") @NotNull String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetDTO setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") @NotNull String maxLength) {
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

    public @Pattern(regexp = "^[^-].*", message = "Area must be positive") @NotNull String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetDTO setNumberOfSheets(@Pattern(regexp = "^[^-].*", message = "Area must be positive") @NotNull String numberOfSheets) {
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
