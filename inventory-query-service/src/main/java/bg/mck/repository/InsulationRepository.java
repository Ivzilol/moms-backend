package bg.mck.repository;

import bg.mck.entity.materialEntity.InsulationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulationRepository extends MongoRepository<InsulationEntity, String > {
}
