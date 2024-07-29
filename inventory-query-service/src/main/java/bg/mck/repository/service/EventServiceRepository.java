package bg.mck.repository.service;

import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventServiceRepository extends MongoRepository<ServiceEvent<? extends BaseServiceEvent>, String> {


    List<ServiceEvent<? extends BaseServiceEvent>> findServiceEventsByEventServiceIdOrderByEventLocalDateTimeAsc(String eventServiceId);

}
