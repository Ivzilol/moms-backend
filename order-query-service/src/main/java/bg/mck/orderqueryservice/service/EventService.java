package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.*;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.exception.OrderNotFoundException;
import bg.mck.orderqueryservice.mapper.ConstructionSiteMapper;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import bg.mck.orderqueryservice.utils.EventTypeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RedisService redisService;
    private final OrderMapper orderMapper;
    private final EventTypeUtils eventTypeUtils;
    private final Gson gson;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ConstructionSiteMapper constructionSiteMapper;
    private final ConstructionSiteService constructionSiteService;

    public EventService(EventRepository eventRepository, RedisService redisService, OrderMapper orderMapper, EventTypeUtils eventTypeUtils, Gson gson, OrderService orderService, OrderRepository orderRepository, ConstructionSiteMapper constructionSiteMapper, ConstructionSiteService constructionSiteService) throws NoSuchMethodException {
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeUtils = eventTypeUtils;
        this.gson = gson;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.constructionSiteMapper = constructionSiteMapper;
        this.constructionSiteService = constructionSiteService;
    }


    @Transactional
    public void processOrderEvent(String data, String eventType) throws InvocationTargetException, IllegalAccessException {
        String materialType = getMaterialType(data);
        eventTypeUtils.getOrderMethodProcessors().get(eventType).invoke(this, data, eventType, materialType);
    }

    private <T extends BaseEvent> void processCreateEvent(String data, String eventType, String materialType) {
        Type eventTypeToken = eventTypeUtils.getTypeEvents().get(eventType).get(materialType);
        OrderEvent<CreateOrderEvent<T>> orderEvent = gson.fromJson(data, eventTypeToken);
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
        orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        saveEvent(orderEvent);
        processEntity(orderEntity);
    }

    private <T extends BaseEvent> void processUpdateEvent(String data, String eventType, String materialType) throws JsonProcessingException {
        Type eventTypeToken = eventTypeUtils.getTypeEvents().get(eventType).get(materialType);
        OrderEvent<CreateOrderEvent<T>> orderEvent = gson.fromJson(data, eventTypeToken);
        OrderEntity newOrderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
        orderRepository.findById(newOrderEntity.getId()).ifPresentOrElse(entity -> {
            entity = newOrderEntity;
            orderRepository.save(entity);
        }, () -> {
            throw new OrderNotFoundException("Order with id: " + newOrderEntity.getId() + "does not exist");
        });

        saveEvent(orderEvent);
        processEntity(newOrderEntity);
    }

    private void processEntity(OrderEntity orderEntity) {
        orderService.saveOrder(orderEntity);
        OrderDTO orderDTO = orderMapper.fromOrderEntityToDTO(orderEntity);
        redisService.cacheOrder(orderDTO);
    }

    private <T extends BaseEvent> void saveEvent(OrderEvent<T> orderEvent) {
        eventRepository.save(orderEvent);
    }

    private String getMaterialType(String data) {
        return Arrays.stream(MaterialType.values()).filter(material -> data.contains(material.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown material type in data: " + data))
                .name();
    }

    @Transactional
    public void processConstructionSiteEvent(String data, String eventType) throws InvocationTargetException, IllegalAccessException {
        this.eventTypeUtils.getConstructionMethodProcessors().get(eventType).invoke(this, data, eventType);
    }

    private void processCreateConstructionSite(String data) {
        Type eventTypeToken = new TypeToken<OrderEvent<ConstructionSiteEvent>>() {
        }.getType();
        OrderEvent<ConstructionSiteEvent> constructionEvent = gson.fromJson(data, eventTypeToken);
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntityFromEvent(constructionEvent.getEvent());
        constructionSiteEntity.setId(String.valueOf(constructionEvent.getEvent().getId()));
        saveEvent(constructionEvent);
        constructionSiteService.saveConstructionSite(constructionSiteEntity);
    }


    private void processUpdateConstructionSite(String data) {
        Type eventTypeToken = new TypeToken<OrderEvent<ConstructionSiteEvent>>() {
        }.getType();
        OrderEvent<ConstructionSiteEvent> constructionEvent = gson.fromJson(data, eventTypeToken);
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntityFromEvent(constructionEvent.getEvent());
        constructionSiteService.updateConstructionSite(constructionSiteEntity);
    }
}
