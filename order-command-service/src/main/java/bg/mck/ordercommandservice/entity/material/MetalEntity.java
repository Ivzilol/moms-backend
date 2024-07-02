package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "metals")
public class MetalEntity  extends BaseEntity {

    private String generalDescription;
    private String length;
    private String weight;
    private String typeOfMetal;
    private String materialStandard;
    private String standardForTolerances;
    private String originCertificate;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public MetalEntity() {
    }

    public MetalEntity(String generalDescription, String length, String weight, String typeOfMetal, String materialStandard, String standardForTolerances, String originCertificate, Double quantity, String note, String specificationFileUrl) {
        this.generalDescription = generalDescription;
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

    public String getGeneralDescription() {
        return generalDescription;
    }

    public MetalEntity setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
        return this;
    }

    public String getLength() {
        return length;
    }

    public MetalEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public MetalEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTypeOfMetal() {
        return typeOfMetal;
    }

    public MetalEntity setTypeOfMetal(String typeOfMetal) {
        this.typeOfMetal = typeOfMetal;
        return this;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public MetalEntity setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
        return this;
    }

    public String getStandardForTolerances() {
        return standardForTolerances;
    }

    public MetalEntity setStandardForTolerances(String standardForTolerances) {
        this.standardForTolerances = standardForTolerances;
        return this;
    }

    public String getOriginCertificate() {
        return originCertificate;
    }

    public MetalEntity setOriginCertificate(String originCertificate) {
        this.originCertificate = originCertificate;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public MetalEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public MetalEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
