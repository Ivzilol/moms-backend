package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByEmail(String email);

    OrderEntity findByOrderNumber(Integer number);
}
