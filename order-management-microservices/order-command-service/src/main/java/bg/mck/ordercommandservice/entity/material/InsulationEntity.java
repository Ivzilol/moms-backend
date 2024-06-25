package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//изолация
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
}
