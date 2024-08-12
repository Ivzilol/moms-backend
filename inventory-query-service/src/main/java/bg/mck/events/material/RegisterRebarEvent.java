package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;

public class RegisterRebarEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private String maxLength;
    private LengthUnits maxLengthUnit;
    private String weight;
    private WeightUnits weightUnit;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RegisterRebarEvent(Long materialId, EventType eventType, String category, String name, String maxLength, LengthUnits maxLengthUnit, String weight, WeightUnits weightUnit, Double quantity, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterRebarEvent() {

    }

    public String getCategory() {
        return category;
    }

    public RegisterRebarEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterRebarEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RegisterRebarEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RegisterRebarEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RegisterRebarEvent setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WeightUnits getWeightUnit() {
        return weightUnit;
    }

    public RegisterRebarEvent setWeightUnit(WeightUnits weightUnit) {
        this.weightUnit = weightUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RegisterRebarEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RegisterRebarEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RegisterRebarEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
