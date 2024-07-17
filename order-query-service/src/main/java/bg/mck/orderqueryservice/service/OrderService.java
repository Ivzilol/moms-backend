package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public void saveOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::fromOrderEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getMyOrders(String email) {
        List<OrderDTO> collect = orderRepository
                .findByEmail(email)
                .stream()
                .map(orderMapper::fromOrderEntityToDTO)
                .collect(Collectors.toList());

        return collect;
    }
}
