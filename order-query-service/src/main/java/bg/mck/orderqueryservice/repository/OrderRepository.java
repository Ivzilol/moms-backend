package bg.mck.orderqueryservice.repository;

import bg.mck.orderqueryservice.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByEmail(String email);

    Optional<OrderEntity> findByOrderNumber(Integer number);
}
