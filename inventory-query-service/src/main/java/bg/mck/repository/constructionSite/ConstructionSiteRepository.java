package bg.mck.repository.constructionSite;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSiteRepository extends MongoRepository<ConstructionSiteEntity, String> {
}
