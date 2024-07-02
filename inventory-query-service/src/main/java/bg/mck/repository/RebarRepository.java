package bg.mck.repository;

import bg.mck.entity.materialEntity.RebarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebarRepository extends MongoRepository<RebarEntity, Long> {
}
