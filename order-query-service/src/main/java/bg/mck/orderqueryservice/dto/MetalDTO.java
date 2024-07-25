package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class MetalDTO extends BaseDTO {

    private String totalWeight;
    private WeightUnits totalWeightUnit;

    public MetalDTO() {
    }

    public MetalDTO(String id, WeightUnits totalWeightUnit, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, String totalWeight) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalDTO setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public WeightUnits getTotalWeightUnit() {
        return totalWeightUnit;
    }

    public MetalDTO setTotalWeightUnit(WeightUnits totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
        return this;
    }
}
