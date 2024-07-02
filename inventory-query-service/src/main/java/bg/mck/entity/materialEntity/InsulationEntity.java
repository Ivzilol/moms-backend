package bg.mck.entity.materialEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "insulation")
public class InsulationEntity {

    @Id
    private String id;
    private String type;
    private String color;
    private String length;
    private String width;
    private Double thickness;
    private Double thermalPerformance;
    private Double density;
    private Double quantity;
    private String note;
    private String specificationFileUrl;


    public InsulationEntity() {
    }

    public InsulationEntity(String id, String type, String color, String length, String width, Double thickness, Double thermalPerformance, Double density, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.thermalPerformance = thermalPerformance;
        this.density = density;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public InsulationEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public InsulationEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public InsulationEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public InsulationEntity setWidth(String width) {
        this.width = width;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public Double getThermalPerformance() {
        return thermalPerformance;
    }

    public InsulationEntity setThermalPerformance(Double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
        return this;
    }

    public Double getDensity() {
        return density;
    }

    public InsulationEntity setDensity(Double density) {
        this.density = density;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public InsulationEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsulationEntity that = (InsulationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(color, that.color) && Objects.equals(length, that.length) && Objects.equals(width, that.width) && Objects.equals(thickness, that.thickness) && Objects.equals(thermalPerformance, that.thermalPerformance) && Objects.equals(density, that.density) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, color, length, width, thickness, thermalPerformance, density, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "InsulationEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", thickness=" + thickness +
                ", thermalPerformance=" + thermalPerformance +
                ", density=" + density +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
