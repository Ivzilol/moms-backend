package bg.mck.repository;

import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalvaniseRepository extends MongoRepository<GalvanisedSheetEntity, String> {
}
