package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class SetDTO extends BaseDTO {

    private String color;

    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String quantity;
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

    public SetDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String color, String maxLength, LengthUnits maxLengthUnit, String quantity, WeightUnits quantityUnit) {
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

    public String getQuantity() {
        return quantity;
    }

    public SetDTO setQuantity(String quantity) {
        this.quantity = quantity;
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
