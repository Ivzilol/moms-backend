package bg.mck.events.material;

import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;

public class RegisterSetEvent extends BaseMaterialEvent {

    private String category;

    private String name;

    private String galvanisedSheetThickness;
    private LengthUnits galvanisedSheetThicknessUnit;

    private String color;

    private String maxLength;
    private LengthUnits maxLengthUnit;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public RegisterSetEvent(Long materialId, EventType eventType, String category, String name, String galvanisedSheetThickness, LengthUnits galvanisedSheetThicknessUnit, String color, String maxLength, LengthUnits maxLengthUnit, Double quantity, String description, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        this.color = color;
        this.maxLength = maxLength;
        this.maxLengthUnit = maxLengthUnit;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterSetEvent() {

    }

    public String getCategory() {
        return category;
    }

    public RegisterSetEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterSetEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getGalvanisedSheetThickness() {
        return galvanisedSheetThickness;
    }

    public RegisterSetEvent setGalvanisedSheetThickness(String galvanisedSheetThickness) {
        this.galvanisedSheetThickness = galvanisedSheetThickness;
        return this;
    }

    public LengthUnits getGalvanisedSheetThicknessUnit() {
        return galvanisedSheetThicknessUnit;
    }

    public RegisterSetEvent setGalvanisedSheetThicknessUnit(LengthUnits galvanisedSheetThicknessUnit) {
        this.galvanisedSheetThicknessUnit = galvanisedSheetThicknessUnit;
        return this;
    }

    public String getColor() {
        return color;
    }

    public RegisterSetEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RegisterSetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public LengthUnits getMaxLengthUnit() {
        return maxLengthUnit;
    }

    public RegisterSetEvent setMaxLengthUnit(LengthUnits maxLengthUnit) {
        this.maxLengthUnit = maxLengthUnit;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RegisterSetEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RegisterSetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RegisterSetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
