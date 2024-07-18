package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.events.BaseEvent;
import bg.mck.orderqueryservice.events.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventRepository extends MongoRepository<OrderEvent<? extends BaseEvent>, String> {

    @Query("{ 'eventOrderId': ?0 }")
    List<OrderEvent<? extends BaseEvent>> findByEventOrderIdOrderByEventTimeAsc(Long orderId);
}
