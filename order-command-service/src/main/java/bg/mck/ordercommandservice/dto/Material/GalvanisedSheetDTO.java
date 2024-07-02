package bg.mck.ordercommandservice.dto.Material;


public class GalvanisedSheetDTO {
    private String marking; //Означение
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public GalvanisedSheetDTO() {
    }

    public GalvanisedSheetDTO(String marking, String number, String type, Double length, Double width, Double area, Double quantity, String note, String specificationFileUrl) {
        this.marking = marking;
        this.number = number;
        this.type = type;
        this.length = length;
        this.width = width;
        this.area = area;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getMarking() {
        return marking;
    }

    public GalvanisedSheetDTO setMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public GalvanisedSheetDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public GalvanisedSheetDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public GalvanisedSheetDTO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetDTO setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public GalvanisedSheetDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanisedSheetDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
