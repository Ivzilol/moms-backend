package bg.mck.repository.transport;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTransportRepository extends MongoRepository<MaterialEvent<? extends BaseEvent>, String> {

}
