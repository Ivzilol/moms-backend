package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity  extends BaseEntity {

    private String marking;
    private String number;
    private String type;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(String marking, String number, String type, Double length, Double width, Double area, Double quantity, String note, String specificationFileUrl) {
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
}
