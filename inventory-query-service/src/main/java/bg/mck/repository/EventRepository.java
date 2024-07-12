package bg.mck.repository;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<MaterialEvent<? extends BaseEvent>, String> {

}
