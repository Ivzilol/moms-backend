package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.WeightUnits;

public class MetalDTO extends BaseDTO {

    private String totalWeight;
    private WeightUnits totalWeightUnit;
    private String kind;

    public MetalDTO() {
    }

    public MetalDTO(String totalWeight, WeightUnits totalWeightUnit, String kind) {
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
        this.kind = kind;
    }

    public MetalDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String totalWeight, WeightUnits totalWeightUnit, String kind) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.totalWeight = totalWeight;
        this.totalWeightUnit = totalWeightUnit;
        this.kind = kind;
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

    public String getKind() {
        return kind;
    }

    public MetalDTO setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
