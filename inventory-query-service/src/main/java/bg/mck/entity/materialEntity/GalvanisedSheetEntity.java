package bg.mck.entity.materialEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "galvanised_sheets")
public class GalvanisedSheetEntity {

    @Id
    private String id;
    private String marking;
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;
    private Double quantity;
    private String note;
    private String specificationFileUrl;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(String id, String marking, String number, String type, Double length, Double width, Double area, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public GalvanisedSheetEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getMarking() {
        return marking;
    }

    public GalvanisedSheetEntity setMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public GalvanisedSheetEntity setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public GalvanisedSheetEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public GalvanisedSheetEntity setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public GalvanisedSheetEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanisedSheetEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalvanisedSheetEntity that = (GalvanisedSheetEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(marking, that.marking) && Objects.equals(number, that.number) && Objects.equals(type, that.type) && Objects.equals(length, that.length) && Objects.equals(width, that.width) && Objects.equals(area, that.area) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marking, number, type, length, width, area, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "GalvanisedSheetEntity{" +
                "id='" + id + '\'' +
                ", marking='" + marking + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", area=" + area +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
