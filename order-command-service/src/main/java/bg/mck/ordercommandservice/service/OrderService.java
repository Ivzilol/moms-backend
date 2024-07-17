package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.*;
import bg.mck.ordercommandservice.mapper.*;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ConstructionSiteService constructionSiteService;
    private final OrderMapper orderMapper;
    private final OrderQueryServiceClient orderQueryServiceClient;
    private final FastenerMapper fastenerMapper;
    private final GalvanisedSheetMapper galvanisedSheetMapper;
    private final InsulationMapper insulationMapper;
    private final MetalMapper metalMapper;
    private final PanelMapper panelMapper;
    private final RebarMapper rebarMapper;
    private final ServiceMapper serviceMapper;
    private final SetMapper setMapper;
    private final TransportMapper transportMapper;
    private final UnspecifiedMapper unspecifiedMapper;

    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, OrderMapper orderMapper, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetMapper galvanisedSheetMapper, InsulationMapper insulationMapper, MetalMapper metalMapper, PanelMapper panelMapper, RebarMapper rebarMapper, ServiceMapper serviceMapper, SetMapper setMapper, TransportMapper transportMapper, UnspecifiedMapper unspecifiedMapper) {
        this.orderRepository = orderRepository;
        this.constructionSiteService = constructionSiteService;
        this.orderMapper = orderMapper;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
        this.insulationMapper = insulationMapper;
        this.metalMapper = metalMapper;
        this.panelMapper = panelMapper;
        this.rebarMapper = rebarMapper;
        this.serviceMapper = serviceMapper;
        this.setMapper = setMapper;
        this.transportMapper = transportMapper;
        this.unspecifiedMapper = unspecifiedMapper;
    }


    public OrderDTO getOrder(Long id) { //FIXME: remove after order query service is implemented
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Long constructionSiteId = orderById.get().getConstructionSite().getId();

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

        orderEntity.setEmail(email)
                .setOrderNumber(lastOrderNumber.orElse(0) + 1)
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderDate(ZonedDateTime.now(z).plusHours(3)) //FIXME: find a better way to set the time and timezone
                .setConstructionSite(constructionSiteByNumberAndName);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} created successfully", orderEntity.getId());

        orderEntity = orderRepository.findById(orderEntity.getId()).get();
        createEvent(orderEntity);

        return new CreateOrderDTO.Builder()
                .orderStatus(orderEntity.getOrderStatus())
                .orderId(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .constructionSiteName(orderEntity.getConstructionSite().getName())
                .constructionSiteNumber(orderEntity.getConstructionSite().getConstructionNumber())
                .build();
    }

    private void createEvent(OrderEntity orderEntity) {
        processAndSendEvent(orderEntity, orderEntity.getFasteners(), fastenerMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getGalvanisedSheets(), galvanisedSheetMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getMetals(), metalMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getPanels(), panelMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getRebars(), rebarMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getServices(), serviceMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getSets(), setMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getTransports(), transportMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getInsulation(), insulationMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getUnspecified(), unspecifiedMapper::toEvent);
    }

    private <M, E> void processAndSendEvent(OrderEntity orderEntity, Set<M> materials, Function<M, E> mapper) {
        if (materials == null || materials.isEmpty()) {
            return;
        }

        Set<E> materialEvents = materials.stream()
                .map(mapper)
                .collect(Collectors.toSet());

        OrderEvent<CreateOrderEvent<E>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);

        CreateOrderEvent<E> createOrderEvent = orderMapper.toEvent(orderEntity);
        createOrderEvent.setOrderId(orderEntity.getId());
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEventTime(LocalDateTime.now());
        createOrderEvent.setMaterials(materialEvents);
        createOrderEvent.setEmail(orderEntity.getEmail());
        orderEvent.setEvent(createOrderEvent);

        orderQueryServiceClient.sendEvent(orderEvent, String.valueOf(OrderEventType.ORDER_CREATED));
    }


}