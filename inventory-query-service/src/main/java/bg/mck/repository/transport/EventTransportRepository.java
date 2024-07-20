package bg.mck.repository.transport;

import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.transport.BaseTransportEvent;
import bg.mck.events.transport.TransportEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTransportRepository extends MongoRepository<TransportEvent<? extends BaseTransportEvent>, String> {

}
