package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class PanelUpdateEvent extends BaseMaterialEvent {

    public PanelUpdateEvent() {
    }

    public PanelUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }
    private String materialType;
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

    public String getMaterialType() {
        return materialType;
    }

    public PanelUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public PanelUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public PanelUpdateEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public PanelUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelUpdateEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public PanelUpdateEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public LengthUnits getLengthUnit() {
        return lengthUnit;
    }

    public PanelUpdateEvent setLengthUnit(LengthUnits lengthUnit) {
        this.lengthUnit = lengthUnit;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public PanelUpdateEvent setWidth(String width) {
        this.width = width;
        return this;
    }

    public LengthUnits getWidthUnit() {
        return widthUnit;
    }

    public PanelUpdateEvent setWidthUnit(LengthUnits widthUnit) {
        this.widthUnit = widthUnit;
        return this;
    }

    public String getTotalThickness() {
        return totalThickness;
    }

    public PanelUpdateEvent setTotalThickness(String totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public LengthUnits getTotalThicknessUnit() {
        return totalThicknessUnit;
    }

    public PanelUpdateEvent setTotalThicknessUnit(LengthUnits totalThicknessUnit) {
        this.totalThicknessUnit = totalThicknessUnit;
        return this;
    }

    public String getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelUpdateEvent setFrontSheetThickness(String frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public LengthUnits getFrontSheetThicknessUnit() {
        return frontSheetThicknessUnit;
    }

    public PanelUpdateEvent setFrontSheetThicknessUnit(LengthUnits frontSheetThicknessUnit) {
        this.frontSheetThicknessUnit = frontSheetThicknessUnit;
        return this;
    }

    public String getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelUpdateEvent setBackSheetThickness(String backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }

    public LengthUnits getBackSheetThicknessUnit() {
        return backSheetThicknessUnit;
    }

    public PanelUpdateEvent setBackSheetThicknessUnit(LengthUnits backSheetThicknessUnit) {
        this.backSheetThicknessUnit = backSheetThicknessUnit;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public PanelUpdateEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
