package bg.mck.orderqueryservice.dto;


import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class RebarDTO extends BaseDTO {

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

    public RebarDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
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
}
