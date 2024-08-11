package bg.mck.repository.transport;

import bg.mck.events.transport.BaseTransportEvent;
import bg.mck.events.transport.TransportEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTransportRepository extends MongoRepository<BaseTransportEvent, String> {

    List<BaseTransportEvent> findTransportEventsByTransportIdOrderByLocalDateTimeAsc(String eventTransportId);
}
