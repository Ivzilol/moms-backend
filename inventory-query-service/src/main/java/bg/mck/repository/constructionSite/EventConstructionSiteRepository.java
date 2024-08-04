package bg.mck.repository.constructionSite;

import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventConstructionSiteRepository extends MongoRepository<BaseConstructionEvent, String> {

    List<BaseConstructionEvent> findConstructionEventByConstructionIdOrderByLocalDateTimeAsc(String eventConstructionId);
}
