package bg.mck.momsbackend.application.repository;

import bg.mck.momsbackend.application.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods go here
}
