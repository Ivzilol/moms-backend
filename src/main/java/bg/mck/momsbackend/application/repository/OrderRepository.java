package bg.mck.momsbackend.application.repository;

import bg.mck.momsbackend.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods go here
}
