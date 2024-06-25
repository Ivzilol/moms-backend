package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//метали
@Entity
@Table(name = "metals")
public class MetalEntity  extends BaseEntity {

    private String generaDescription;
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
}
