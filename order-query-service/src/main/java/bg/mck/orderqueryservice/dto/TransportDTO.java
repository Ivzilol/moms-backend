package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.LengthUnits;
import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class TransportDTO extends BaseDTO {

    private String maxLength;
    private LengthUnits maxLengthUnit;

    private String weight;
    private WeightUnits weightUnit;

    private String truck;
    private String quantity;

    public TransportDTO() {
    }

    public TransportDTO(String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit, String truck, String quantity) {
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.truck = truck;
        this.quantity = quantity;
    }

    public TransportDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit, String truck, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.truck = truck;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public TransportDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
