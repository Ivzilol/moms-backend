package bg.mck.ordercommandservice.dto.Material;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FastenerDTO {
    @NotNull
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    @DecimalMin(value = "0.00")
    @NotNull
    private Double quantity;
    @Size(min = 5, message = "Note must be at least 5 characters long.")
    private String note;
    private String specificationFileUrl;

    public FastenerDTO() {
    }

    public FastenerDTO(String description, String diameter, Double length, String model, String clazz, Double quantity, String note, String specificationFileUrl) {
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getDescription() {
        return description;
    }

    public FastenerDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FastenerDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public FastenerDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FastenerDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FastenerDTO setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public FastenerDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public FastenerDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FastenerDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
