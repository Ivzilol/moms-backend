package bg.mck.orderqueryservice.dto;

import bg.mck.orderqueryservice.entity.enums.AreaUnits;
import bg.mck.orderqueryservice.entity.enums.LengthUnits;

public class InsulationDTO extends BaseDTO{

    private String type;

    private String thickness;
    private LengthUnits thicknessUnit;
    private String quantity;
    private AreaUnits quantityUnit;

    public InsulationDTO() {
    }

    public InsulationDTO(String type, String thickness, LengthUnits thicknessUnit, String quantity, AreaUnits quantityUnit) {
        this.type = type;
        this.thickness = thickness;
        this.thicknessUnit = thicknessUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public InsulationDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus, String type, String thickness, LengthUnits thicknessUnit, String quantity, AreaUnits quantityUnit) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.thickness = thickness;
        this.thicknessUnit = thicknessUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public String getType() {
        return type;
    }

    public InsulationDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationDTO setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public LengthUnits getThicknessUnit() {
        return thicknessUnit;
    }

    public InsulationDTO setThicknessUnit(LengthUnits thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public InsulationDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public AreaUnits getQuantityUnit() {
        return quantityUnit;
    }

    public InsulationDTO setQuantityUnit(AreaUnits quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }
}
