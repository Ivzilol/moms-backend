package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.*;

//крепежи
@Entity
@Table(name = "fastener")
public class FastenerEntity extends BaseEntity {

    private String description;
    private Double diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String Note;



    public FastenerEntity() {
    }

    public FastenerEntity(String description, Double diameter, Double length, String model, String clazz, Double quantity, String note, _MaterialEntity material) {
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        Note = note;

    }

    public String getDescription() {
        return description;
    }

    public FastenerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public FastenerEntity setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public FastenerEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FastenerEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public FastenerEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return Note;
    }

    public FastenerEntity setNote(String note) {
        Note = note;
        return this;
    }


}
