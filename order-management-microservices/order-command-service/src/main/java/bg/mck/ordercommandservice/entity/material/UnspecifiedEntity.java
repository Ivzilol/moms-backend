package bg.mck.ordercommandservice.entity.material;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//други

public class UnspecifiedEntity  extends BaseEntity {
    private String name;
    private Double quantity;
}
