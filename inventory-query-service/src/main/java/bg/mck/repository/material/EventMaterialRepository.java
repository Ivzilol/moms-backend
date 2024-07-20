package bg.mck.repository.material;

import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMaterialRepository extends MongoRepository<MaterialEvent<? extends BaseMaterialEvent>, String> {

    @Query("{ 'event.materialId': ?0, 'event.category': ?1 }")
    List<MaterialEvent<? extends BaseMaterialEvent>> findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(
            Long materialId, String category);

//    List<MaterialEvent<? extends BaseEvent>> findMaterialEventByMaterialTypeAndEventMaterialId(MaterialType materialType, Long materialId);
}
