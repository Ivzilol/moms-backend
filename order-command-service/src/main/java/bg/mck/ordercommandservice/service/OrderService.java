package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.event.OrderEvent;
import bg.mck.ordercommandservice.event.OrderEventType;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ConstructionSiteService constructionSiteService;
    private final OrderMapper orderMapper;
    private final OrderQueryServiceClient orderQueryServiceClient;
    private final FastenerMapper fastenerMapper;

    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, OrderMapper orderMapper, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper) {
        this.orderRepository = orderRepository;
        this.constructionSiteService = constructionSiteService;
        this.orderMapper = orderMapper;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
    }


    public OrderDTO getOrder(Long id) {
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Long constructionSiteId = orderById.get().getConstructionSite().getId();

            OrderEntity orderEntity = orderById.get();
            OrderDTO orderDTO = orderMapper.toOrderDTO(orderById.get());
            orderDTO.setConstructionSite(constructionSiteService.getConstructionSite(constructionSiteId));


            return orderDTO;
        }
        throw new OrderNotFoundException("Order with id " + id + " not found");
    }

    @Transactional
    public CreateOrderDTO createOrder(OrderDTO order, String email) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByNumberAndName = constructionSiteService.getConstructionSiteByNumberAndName(order.getConstructionSite());
        Optional<Integer> lastOrderNumber = orderRepository.findLastOrderNumber();

        ZoneId z = ZoneId.of("Europe/Sofia");

        orderEntity.setUsername(email)
                .setOrderNumber(lastOrderNumber.orElse(0) + 1)
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderDate(ZonedDateTime.now(z).plusHours(3)) //FIXME: find a better way to set the time and timezone
                .setConstructionSite(constructionSiteByNumberAndName);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} created successfully", orderEntity.getId());

        orderEntity = orderRepository.findById(orderEntity.getId()).get();

        if (!orderEntity.getFasteners().isEmpty()){
            Set<FasterEvent> fasteners = new HashSet<>();
                    orderEntity.getFasteners()
                    .forEach(fastener -> {
                        fasteners.add(fastenerMapper.toEvent(fastener));
                    });
            OrderEvent<CreateOrderEvent<FasterEvent>> orderEvent = new OrderEvent<>();
            orderEvent.setEventType(OrderEventType.ORDER_CREATED);

            CreateOrderEvent<FasterEvent> createOrderEvent = orderMapper.toCreateOrderEvent(orderEntity);
            orderEvent.setEvent(createOrderEvent);

            orderQueryServiceClient.sendEvent(orderEvent, String.valueOf(OrderEventType.ORDER_CREATED));
        }



        return new CreateOrderDTO.Builder()
                .orderId(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .constructionSiteName(orderEntity.getConstructionSite().getName())
                .constructionSiteNumber(orderEntity.getConstructionSite().getConstructionNumber())
                .build();
    }

}
