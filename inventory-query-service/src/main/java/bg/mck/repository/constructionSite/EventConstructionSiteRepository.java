package bg.mck.repository.constructionSite;

import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventConstructionSiteRepository extends MongoRepository<ConstructionEvent<? extends BaseConstructionEvent>, String> {

}
