package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.exception.OrderNotFoundException;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrderEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getMyOrders(String email) {
        return orderRepository
                .findByEmail(email)
                .stream()
                .map(orderMapper::fromOrderEntityToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id.toString())
                .map(this.orderMapper::fromOrderEntityToDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found in db"));
    }

    public OrderDTO getOrderByOrderNumber(Integer number) {
        return orderRepository
                .findByOrderNumber(number)
                .map(orderMapper::fromOrderEntityToDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order with number " + number + " not found"));
    }
}
