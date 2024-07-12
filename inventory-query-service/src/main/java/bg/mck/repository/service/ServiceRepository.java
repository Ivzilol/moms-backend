package bg.mck.repository.service;

import bg.mck.entity.serviceEntity.ServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends MongoRepository<ServiceEntity, String> {
}
