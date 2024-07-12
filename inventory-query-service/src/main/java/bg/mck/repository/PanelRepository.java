package bg.mck.repository;

import bg.mck.entity.materialEntity.PanelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelRepository extends MongoRepository<PanelEntity, String> {
}
