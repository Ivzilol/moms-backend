package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class UpdatePanelEvent extends BaseMaterialEvent {

    public UpdatePanelEvent() {
    }

    public UpdatePanelEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }

    public UpdatePanelEvent(Long materialId, EventType eventType, String category, String name, String materialType, String type, String color, String length, LengthUnits lengthUnit, String width, LengthUnits widthUnit, String totalThickness, LengthUnits totalThicknessUnit, String frontSheetThickness, LengthUnits frontSheetThicknessUnit, String backSheetThickness, LengthUnits backSheetThicknessUnit, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.materialType = materialType;
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

    private String category;
    private String name;
    private String materialType;
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

    public String getCategory() {
        return category;
    }

    public UpdatePanelEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdatePanelEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public UpdatePanelEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdatePanelEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public UpdatePanelEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public UpdatePanelEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public UpdatePanelEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public UpdatePanelEvent setWidth(String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public UpdatePanelEvent setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public UpdatePanelEvent setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public UpdatePanelEvent setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public UpdatePanelEvent setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public UpdatePanelEvent setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public UpdatePanelEvent setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public UpdatePanelEvent setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public UpdatePanelEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdatePanelEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
