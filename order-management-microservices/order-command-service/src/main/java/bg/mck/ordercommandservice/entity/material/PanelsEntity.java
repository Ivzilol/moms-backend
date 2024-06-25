package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//панели
@Entity
@Table(name = "panels")
public class PanelsEntity  extends BaseEntity {

    private String type;
    private String color;
    private Double length;
    private Double width;
    private Double totalThickness;
    private Double sheetThickness;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;
}
