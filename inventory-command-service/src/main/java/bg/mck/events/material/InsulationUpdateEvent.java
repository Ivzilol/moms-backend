package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class InsulationUpdateEvent extends BaseMaterialEvent {

    public InsulationUpdateEvent() {
    }

    public InsulationUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    private String materialType;
    private String category;

    private String name;

    private String type;

    private String thickness;
    private LengthUnits thicknessUnit;
    private Double quantity;

    private String description;

    private String specificationFileUrl;


    public String getCategory() {
        return category;
    }

    public InsulationUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public InsulationUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationUpdateEvent setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationUpdateEvent setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public InsulationUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }
}
