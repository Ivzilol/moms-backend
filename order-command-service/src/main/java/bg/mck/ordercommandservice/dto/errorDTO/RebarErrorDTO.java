package bg.mck.ordercommandservice.dto.errorDTO;

public class RebarErrorDTO {
    private String positionNumber;
    private String type;
    private String steel;
    private String diameter;
    private String length;
    private String weight;
    private String quantity;
    private String note;
    private String specificationFileUrl;

    public RebarErrorDTO() {
    }

    public RebarErrorDTO(String positionNumber, String type, String steel, String diameter, String length, String weight, String quantity, String note, String specificationFileUrl) {
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

    public String getPositionNumber() {
        return positionNumber;
    }

    public RebarErrorDTO setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    public String getType() {
        return type;
    }

    public RebarErrorDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getSteel() {
        return steel;
    }

    public RebarErrorDTO setSteel(String steel) {
        this.steel = steel;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public RebarErrorDTO setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public RebarErrorDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarErrorDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public RebarErrorDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RebarErrorDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarErrorDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
