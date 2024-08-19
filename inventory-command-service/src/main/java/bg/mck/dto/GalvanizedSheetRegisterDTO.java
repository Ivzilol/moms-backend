package bg.mck.dto;

import bg.mck.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GalvanizedSheetRegisterDTO  extends BaseDTO{

    @NotNull(message = "Type is required")
    private String type;
    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull
    private String maxLength;
    private LengthUnits maxLengthUnit;
    @Pattern(regexp = "^[^-].*", message = "Number of sheets must be positive")
    private String numberOfSheets;

    public @NotNull(message = "Type is required") String getType() {
        return type;
    }

    public GalvanizedSheetRegisterDTO setType(@NotNull(message = "Type is required") String type) {
        this.type = type;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") @NotNull String getMaxLength() {
        return maxLength;
    }

    public GalvanizedSheetRegisterDTO setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") @NotNull String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public GalvanizedSheetRegisterDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Number of sheets must be positive") String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanizedSheetRegisterDTO setNumberOfSheets(@Pattern(regexp = "^[^-].*", message = "Number of sheets must be positive") String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }
}
