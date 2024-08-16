package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "SELECT MAX(orderNumber) FROM OrderEntity")
    Optional<Integer> findLastOrderNumber();

    OrderEntity findByOrderNumber(Integer orderNumber);
}
