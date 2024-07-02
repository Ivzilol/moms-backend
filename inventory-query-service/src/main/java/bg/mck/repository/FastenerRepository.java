package bg.mck.repository;

import bg.mck.entity.materialEntity.FastenerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastenerRepository extends MongoRepository<FastenerEntity, Long> {

    FastenerEntity findByName(String name);
}
