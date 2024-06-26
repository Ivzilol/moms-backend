package bg.mck.ordercommandservice.dto.Material;


public class InsulationDTO {
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

    public InsulationDTO() {
    }

    public InsulationDTO(String type, String color, String length, String width, Double thickness, Double thermalPerformance, Double density, Double quantity, String note, String specificationFileUrl) {
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

    public InsulationDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public InsulationDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLength() {
        return length;
    }

    public InsulationDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public InsulationDTO setWidth(String width) {
        this.width = width;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public InsulationDTO setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public Double getThermalPerformance() {
        return thermalPerformance;
    }

    public InsulationDTO setThermalPerformance(Double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
        return this;
    }

    public Double getDensity() {
        return density;
    }

    public InsulationDTO setDensity(Double density) {
        this.density = density;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public InsulationDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public InsulationDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
