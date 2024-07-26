package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class TransportDTO extends BaseDTO {

    private String maxLength;
    private LengthUnits maxLengthUnit;

    private String weight;
    private WeightUnits weightUnit;

    private String truck;

    public TransportDTO() {
    }

    public TransportDTO(String id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit, String truck) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.truck = truck;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public TransportDTO setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public TransportDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public TransportDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public TransportDTO setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportDTO setTruck(String truck) {
        this.truck = truck;
        return this;
    }
}
