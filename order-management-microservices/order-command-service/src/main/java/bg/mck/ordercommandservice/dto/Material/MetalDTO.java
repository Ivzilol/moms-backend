package bg.mck.ordercommandservice.dto.Material;

public class MetalDTO {

    private String generaDescription;
    private String length;
    private String weight;
    private String typeOfMetal;
    private String materialStandard;
    private String standardForTolerances;
    private String originCertificate;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public MetalDTO() {
    }

    public MetalDTO(String generaDescription, String length, String weight, String typeOfMetal, String materialStandard, String standardForTolerances, String originCertificate, Double quantity, String note, String specificationFileUrl) {
        this.generaDescription = generaDescription;
        this.length = length;
        this.weight = weight;
        this.typeOfMetal = typeOfMetal;
        this.materialStandard = materialStandard;
        this.standardForTolerances = standardForTolerances;
        this.originCertificate = originCertificate;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getGeneraDescription() {
        return generaDescription;
    }

    public MetalDTO setGeneraDescription(String generaDescription) {
        this.generaDescription = generaDescription;
        return this;
    }

    public String getLength() {
        return length;
    }

    public MetalDTO setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public MetalDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTypeOfMetal() {
        return typeOfMetal;
    }

    public MetalDTO setTypeOfMetal(String typeOfMetal) {
        this.typeOfMetal = typeOfMetal;
        return this;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public MetalDTO setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
        return this;
    }

    public String getStandardForTolerances() {
        return standardForTolerances;
    }

    public MetalDTO setStandardForTolerances(String standardForTolerances) {
        this.standardForTolerances = standardForTolerances;
        return this;
    }

    public String getOriginCertificate() {
        return originCertificate;
    }

    public MetalDTO setOriginCertificate(String originCertificate) {
        this.originCertificate = originCertificate;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public MetalDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
