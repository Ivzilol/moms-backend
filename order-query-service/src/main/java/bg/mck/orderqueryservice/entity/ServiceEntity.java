package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("services")
public class ServiceEntity extends BaseMaterialEntity {

    public ServiceEntity() {
    }
}
