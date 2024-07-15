package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderEntity order) {
        orderRepository.save(order);
    }

}
