package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEventService { //TODO remove after

    private final OrderRepository orderRepository;
    private final EventMapperService eventMapperService;

    @Autowired
    public OrderEventService(OrderRepository orderRepository, EventMapperService eventMapperService) {
        this.orderRepository = orderRepository;
        this.eventMapperService = eventMapperService;
    }

    @Transactional
    public OrderConfirmationDTO createOrderEvent(OrderEntity orderEntity) {
        orderEntity = orderRepository.findById(orderEntity.getId()).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        eventMapperService.mapEvent(orderEntity);

        return new OrderConfirmationDTO.Builder()
                .orderStatus(orderEntity.getOrderStatus())
                .orderId(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .constructionSiteName(orderEntity.getConstructionSite().getName())
                .constructionSiteNumber(orderEntity.getConstructionSite().getConstructionNumber())
                .build();
    }
}
