package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RegisterPanelEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private String type;

    private String color;

    private String length;
    private LengthUnits lengthUnit;
    private String width;
    private LengthUnits widthUnit;
    private String totalThickness;
    private LengthUnits totalThicknessUnit;
    private String frontSheetThickness;
    private LengthUnits frontSheetThicknessUnit;
    private String backSheetThickness;
    private LengthUnits backSheetThicknessUnit;


    private String description;

    private String specificationFileUrl;

    public RegisterPanelEvent() {
    }

    public RegisterPanelEvent(Long materialId, EventType eventType, String category, String name, String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.type = type;
        this.color = color;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.width = width;
        this.widthUnit = widthUnit;
        this.totalThickness = totalThickness;
        this.totalThicknessUnit = totalThicknessUnit;
        this.frontSheetThickness = frontSheetThickness;
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        this.backSheetThickness = backSheetThickness;
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getCategory() {
        return category;
    }

    public RegisterPanelEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterPanelEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RegisterPanelEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public RegisterPanelEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public RegisterPanelEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public RegisterPanelEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public RegisterPanelEvent setWidth(String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public RegisterPanelEvent setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public RegisterPanelEvent setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public RegisterPanelEvent setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public RegisterPanelEvent setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public RegisterPanelEvent setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public RegisterPanelEvent setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public RegisterPanelEvent setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public RegisterPanelEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RegisterPanelEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
