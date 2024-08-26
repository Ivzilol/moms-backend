package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class UpdateInsulationEvent extends BaseMaterialEvent {

    public UpdateInsulationEvent() {
    }

    public UpdateInsulationEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdateInsulationEvent(Long materialId, EventType eventType, String category, String name, String materialType, String type, String thickness, LengthUnits thicknessUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.materialType = materialType;
        this.type = type;
        this.thickness = thickness;
        this.thicknessUnit = thicknessUnit;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    private String category;
    private String name;
    private String materialType;
    private String type;
    private String thickness;
    private LengthUnits thicknessUnit;
    private String description;
    private String specificationFileUrl;

    public String getCategory() {
        return category;
    }

    public UpdateInsulationEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateInsulationEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdateInsulationEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateInsulationEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public UpdateInsulationEvent setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public UpdateInsulationEvent setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public UpdateInsulationEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateInsulationEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
