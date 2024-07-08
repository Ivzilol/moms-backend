package bg.mck.repository;

import bg.mck.enums.MaterialType;
import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMaterialRepository extends MongoRepository<MaterialEvent<? extends BaseEvent>, String> {


    List<MaterialEvent<? extends BaseEvent>> findMaterialEventByMaterialTypeAndEventMaterialId(MaterialType materialType, Long materialId);
}
