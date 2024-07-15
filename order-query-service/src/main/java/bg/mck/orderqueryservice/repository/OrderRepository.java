package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    OrderEntity findByOrderNumber(String orderNumber);
}
