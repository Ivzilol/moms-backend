package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Окомплектовки/обшивки??
@Entity
@Table(name = "sets")
public class SetEntity  extends BaseEntity {

    @Column(columnDefinition="TEXT")
    private String description;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;
}
