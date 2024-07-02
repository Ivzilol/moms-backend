package bg.mck.repository;

import bg.mck.entity.materialEntity.UnspecifiedEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnspecifiedRepository extends MongoRepository<UnspecifiedEntity, Long> {
}
