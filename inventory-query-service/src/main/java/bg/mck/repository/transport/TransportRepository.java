package bg.mck.repository.transport;

import bg.mck.entity.transportEntity.TransportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends MongoRepository<TransportEntity, String> {
}
