package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SetDTO extends BaseDTO {
    @NotNull(message = "Color is required")
    private String color;

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    @NotNull(message = "Length is required")
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String quantity;


    public SetDTO() {
    }

    public SetDTO(String color, String maxLength, LengthUnits maxLengthUnit, String quantity) {
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
    }

    public SetDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String color, String maxLength, LengthUnits maxLengthUnit, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
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
}
