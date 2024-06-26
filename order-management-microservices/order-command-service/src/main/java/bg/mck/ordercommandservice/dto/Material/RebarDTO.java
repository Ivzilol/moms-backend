package bg.mck.ordercommandservice.dto.Material;

public class RebarDTO {
    private Integer positionNumber;
    private String type;
    private String steel;
    private Double diameter;
    private Double length;
    private Double weight;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public RebarDTO() {
    }

    public RebarDTO(Integer positionNumber, String type, String steel, Double diameter, Double length, Double weight, Double quantity, String note, String specificationFileUrl) {
        this.positionNumber = positionNumber;
        this.type = type;
        this.steel = steel;
        this.diameter = diameter;
        this.length = length;
        this.weight = weight;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public RebarDTO setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    public String getType() {
        return type;
    }

    public RebarDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getSteel() {
        return steel;
    }

    public RebarDTO setSteel(String steel) {
        this.steel = steel;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public RebarDTO setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public RebarDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public RebarDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RebarDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
