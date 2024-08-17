package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import jakarta.validation.constraints.Pattern;

public class TransportDTO extends BaseDTO {

    @Pattern(regexp = "^[^-].*", message = "Length must be positive")
    private String maxLength;
    private LengthUnits maxLengthUnit;
    @Pattern(regexp = "^[^-].*", message = "Weight must be positive")
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

    public TransportDTO(Long id, String description, String specificationFileUrl, String adminNote, String materialStatus, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit, String truck, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.truck = truck;
        this.quantity = quantity;
    }

    public @Pattern(regexp = "^[^-].*", message = "Length must be positive") String getMaxLength() {
        return maxLength;
    }

    public TransportDTO setMaxLength(@Pattern(regexp = "^[^-].*", message = "Length must be positive") String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public TransportDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public TransportDTO setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public @Pattern(regexp = "^[^-].*", message = "Weight must be positive") String getWeight() {
        return weight;
    }

    public TransportDTO setWeight(@Pattern(regexp = "^[^-].*", message = "Weight must be positive") String weight) {
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
