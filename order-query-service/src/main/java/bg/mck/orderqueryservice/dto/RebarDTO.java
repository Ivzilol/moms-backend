package bg.mck.orderqueryservice.dto;


import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class RebarDTO extends BaseDTO {

    private String maxLength;
    private LengthUnits maxLengthUnit;

    private String weight;
    private WeightUnits weightUnit;

    public RebarDTO() {
    }

    public RebarDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
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

    public String getWeight() {
        return weight;
    }

    public RebarDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public RebarDTO setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }
}
