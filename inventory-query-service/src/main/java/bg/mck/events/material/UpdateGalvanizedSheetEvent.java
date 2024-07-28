package bg.mck.events.material;

import bg.mck.enums.EventType;

public class UpdateGalvanizedSheetEvent extends BaseMaterialEvent {
    private String category;
    private String name;
    private String materialType;
    private String marking;
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public UpdateGalvanizedSheetEvent() {
    }

    public UpdateGalvanizedSheetEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }


    public String getMaterialType() {
        return materialType;
    }

    public UpdateGalvanizedSheetEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getMarking() {
        return marking;
    }

    public UpdateGalvanizedSheetEvent setMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public UpdateGalvanizedSheetEvent setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public UpdateGalvanizedSheetEvent setType(String type) {
        this.type = type;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public UpdateGalvanizedSheetEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public UpdateGalvanizedSheetEvent setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public UpdateGalvanizedSheetEvent setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UpdateGalvanizedSheetEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UpdateGalvanizedSheetEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UpdateGalvanizedSheetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateGalvanizedSheetEvent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateGalvanizedSheetEvent setName(String name) {
        this.name = name;
        return this;
    }
}
