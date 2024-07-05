package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceEntity extends BaseMaterialEntity {

    public ServiceEntity() {
    }
}
