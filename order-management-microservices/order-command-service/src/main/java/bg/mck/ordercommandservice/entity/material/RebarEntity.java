package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//армировка
@Entity
@Table(name = "rebars")
public class RebarEntity extends BaseEntity {

    private Integer positionNumber;
    private String type;
    private String steel;
    private Double diameter;
    private Double length;
    private Double weight;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public RebarEntity() {
    }
}
