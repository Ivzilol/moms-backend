package bg.mck.userqueryservice.application.repository;

import bg.mck.userqueryservice.application.events.BaseEvent;
import bg.mck.userqueryservice.application.events.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<UserEvent<? extends BaseEvent>, String> {

    List<UserEvent<? extends BaseEvent>> findByEventUserIdOrderByEventLocalDateTimeAsc(Long userId);

}
