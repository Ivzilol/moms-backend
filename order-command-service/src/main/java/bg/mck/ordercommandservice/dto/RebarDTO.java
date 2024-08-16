package bg.mck.ordercommandservice.dto;


import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import jakarta.validation.constraints.Pattern;

public class RebarDTO extends BaseDTO {

//    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String quantity;
    private WeightUnits quantityUnit;

    public RebarDTO() {
    }

    public RebarDTO(String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public RebarDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public String getQuantity() {
        return quantity;
    }

    public RebarDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public WeightUnits getQuantityUnit() {
        return quantityUnit;
    }

    public RebarDTO setQuantityUnit(WeightUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RebarDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

}
