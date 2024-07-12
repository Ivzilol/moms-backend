package bg.mck.repository.material;

import bg.mck.entity.materialEntity.MetalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalRepository extends MongoRepository<MetalEntity, String> {
}
