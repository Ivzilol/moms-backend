package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SetDTO extends BaseDTO {

    @NotNull(message = "Color is required")
    private String color;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull(message = "Length is required")
    private String maxLength;
    @NotNull(message = "Length unit is required")
    private LengthUnits maxLengthUnit;

    @Pattern(regexp = "^[^-].*", message = "Quantity must be positive")
    @NotNull(message = "Quantity is required")
    private String quantity;
    @NotNull(message = "Quantity unit is required")
    private WeightUnits quantityUnit;

    public SetDTO() {
    }

    public SetDTO(String color, String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public SetDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String color, String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public String getColor() {
        return color;
    }

    public SetDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public SetDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public SetDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public WeightUnits getQuantityUnit() {
        return quantityUnit;
    }

    public SetDTO setQuantityUnit(WeightUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }
}
