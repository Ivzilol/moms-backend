package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConstructionSiteRepository extends MongoRepository<ConstructionSiteEntity, String> {

    Optional<ConstructionSiteEntity> findByName(String constructionSiteName);
}
