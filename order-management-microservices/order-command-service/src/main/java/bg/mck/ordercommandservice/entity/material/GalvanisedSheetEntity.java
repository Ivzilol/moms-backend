package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.*;

//Ламарина
@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity  extends BaseEntity {

    private String marking; //Означение
    private String number;
    private Integer count;
    private Double length;
    private Double width;
    private Double area;

    private Double quantity;
    @Column(columnDefinition="TEXT")
    private String note;
    private String specificationFileUrl;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
