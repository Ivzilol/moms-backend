package bg.mck.dto;

import bg.mck.enums.WeightUnits;

public class MetalDTO implements MaterialDTO{

    private String id;

    private String name;

    private String totalWeight;
    private WeightUnits totalWeightUnit;

    private String kind;

    private String description;

    private String specificationFileUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
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
