package bg.mck.repository;

import bg.mck.enums.MaterialType;
import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMaterialRepository extends MongoRepository<MaterialEvent<? extends BaseEvent>, String> {
    @Query("{ 'event.materialId': ?0, 'event.category': ?1 }")
    List<MaterialEvent<? extends BaseEvent>> findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(
            Long materialId, String category);

//    List<MaterialEvent<? extends BaseEvent>> findMaterialEventByMaterialTypeAndEventMaterialId(MaterialType materialType, Long materialId);
}
