package bg.mck.repository.material;

import bg.mck.entity.materialEntity.SetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends MongoRepository<SetEntity, String> {
}
