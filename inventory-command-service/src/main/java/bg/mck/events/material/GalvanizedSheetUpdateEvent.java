package bg.mck.events.material;

import bg.mck.enums.EventType;
import jakarta.persistence.Column;

public class GalvanizedSheetUpdateEvent extends BaseMaterialEvent {
    private String category;
    private String materialType;
    private String marking;
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    @Column(columnDefinition = "TEXT")
    private String note;
    private String specificationFileUrl;

    public GalvanizedSheetUpdateEvent() {
    }

    public GalvanizedSheetUpdateEvent(Long materialId, EventType eventType) {
        super(materialId, eventType);
    }


    public String getMaterialType() {
        return materialType;
    }

    public GalvanizedSheetUpdateEvent setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getMarking() {
        return marking;
    }

    public GalvanizedSheetUpdateEvent setMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public GalvanizedSheetUpdateEvent setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanizedSheetUpdateEvent setType(String type) {
        this.type = type;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public GalvanizedSheetUpdateEvent setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public GalvanizedSheetUpdateEvent setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanizedSheetUpdateEvent setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanizedSheetUpdateEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public GalvanizedSheetUpdateEvent setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanizedSheetUpdateEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public GalvanizedSheetUpdateEvent setCategory(String category) {
        this.category = category;
        return this;
    }
}
