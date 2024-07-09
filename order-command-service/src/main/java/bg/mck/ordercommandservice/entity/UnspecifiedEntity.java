package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "unspecified")
public class UnspecifiedEntity  extends BaseMaterialEntity {

    public UnspecifiedEntity() {
    }
}
