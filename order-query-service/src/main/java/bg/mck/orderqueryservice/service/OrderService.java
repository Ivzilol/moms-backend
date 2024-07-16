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
        try {
            return redisService.getCachedObjects()
                    .stream()
                    .map(orderMapper::fromOrderEntityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return orderRepository.findAll()
                    .stream()
                    .map(orderMapper::fromOrderEntityToDTO)
                    .collect(Collectors.toList());
        }
    }

    public List<OrderDTO> getMyOrders(String email) {
        List<OrderDTO> collect = orderRepository
                .findByEmail(email)
                .stream()
                .map(orderMapper::fromOrderEntityToDTO)
                .collect(Collectors.toList());

        return collect;
    }

    public OrderDTO getOrderById(Long id) {
        String orderId = String.valueOf(id);
        OrderEntity orderEntity = redisService.getCachedObjectById(id);
        if (orderEntity != null) {
            return orderMapper.fromOrderEntityToDTO(orderEntity);
        } else {
            throw new IllegalArgumentException("Order with id: " + orderId + " not found");
        }
    }

    public OrderDTO getOrderByOrderNumber(Integer number) {
        OrderEntity entity = orderRepository.findByOrderNumber(number);
        if (entity == null) {
            throw new IllegalArgumentException("Order with number: " + number + " not found");
        }
        return orderMapper.fromOrderEntityToDTO(entity);
    }
}
