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
    private final RedisService redisService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, RedisService redisService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.redisService = redisService;
    }

    public void saveOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> cachedOrders = redisService.getCachedObjects();
        if (!cachedOrders.isEmpty()) {
            return cachedOrders;
        }

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
        return Optional.ofNullable(redisService.getCachedObjectById(id))
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
    }

    public OrderDTO getOrderByOrderNumber(Integer number) {
        return orderRepository
                .findByOrderNumber(number)
                .map(orderMapper::fromOrderEntityToDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order with number " + number + " not found"));
    }

    public String clearBase() {
        redisService.clearCacheInDatabase();
        orderRepository.deleteAll();
        return "Database is empty now";
    }
}
