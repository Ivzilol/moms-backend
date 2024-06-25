package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.repository.OrderRepository;
import mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderEntity getOrder(Long id) {
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {

            OrderEntity orderEntity = orderById.get();

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderDate(orderEntity.getOrderDate());
            orderDTO.setOrderStatus(orderEntity.getOrderStatus().toString());
            orderDTO.setOrderDescription(orderEntity.getOrderDescription());
            orderDTO.setOrderNumber(orderEntity.getOrderNumber());
            orderDTO.setDeliveryDate(orderEntity.getDeliveryDate());
            orderDTO.setUsername(orderEntity.getUsername());

            return orderEntity;
        }
        return null;
    }
}
