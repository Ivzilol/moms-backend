package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.events.BaseEvent;
import bg.mck.orderqueryservice.events.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<OrderEvent<? extends BaseEvent>, String> {
    List<OrderEvent<? extends BaseEvent>> findByEventOrderIdOrderByEventLocalDateTimeAsc(Long orderId);
}
