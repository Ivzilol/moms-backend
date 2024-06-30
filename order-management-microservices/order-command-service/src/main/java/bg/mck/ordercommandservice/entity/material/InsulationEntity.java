package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "insulation")
public class InsulationEntity  extends BaseEntity {

    private String type;
    private String color;
    private String length;
    private String width;
    private Double thickness;
    private Double thermalPerformance;
    private Double density;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public InsulationEntity() {
    }

    public InsulationEntity(String type, String color, String length, String width, Double thickness, Double thermalPerformance, Double density, Double quantity, String note, String specificationFileUrl) {
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
}
