package bg.mck.userqueryservice.application.repository;

import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository<T extends BaseEvent> extends MongoRepository<UserEvent<T>, String> {
}
