package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.client.NotificationServiceClient;
import bg.mck.orderqueryservice.dto.EmailDTO;
import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.exception.OrderNotFoundException;
import bg.mck.orderqueryservice.mapper.ConstructionSiteMapper;
import bg.mck.orderqueryservice.mapper.MailMapper;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import bg.mck.orderqueryservice.utils.EventTypeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private final NotificationServiceClient notificationServiceClient;
    private final MailMapper mailMapper;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EventService(EventRepository eventRepository, RedisService redisService, OrderMapper orderMapper, EventTypeUtils eventTypeUtils, Gson gson, OrderService orderService, OrderRepository orderRepository, ConstructionSiteMapper constructionSiteMapper, ConstructionSiteService constructionSiteService, NotificationServiceClient notificationServiceClient, MailMapper mailMapper) throws NoSuchMethodException {
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeUtils = eventTypeUtils;
        this.gson = gson;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.constructionSiteMapper = constructionSiteMapper;
        this.constructionSiteService = constructionSiteService;
        this.notificationServiceClient = notificationServiceClient;
        this.mailMapper = mailMapper;
    }


    @Transactional
    public void processOrderEvent(String data, String eventType) throws InvocationTargetException, IllegalAccessException {
        String materialType = getMaterialType(data);
        invokeOrderProcessor(eventType, data, materialType);
    }

    private void invokeOrderProcessor(String eventType, String data, String materialType) throws InvocationTargetException, IllegalAccessException {
        eventTypeUtils.getOrderMethodProcessors().get(eventType).invoke(this, data, eventType, materialType);
    }

    private <T extends BaseEvent> void processCreateEvent(String data, String eventType, String materialType) {
        OrderEvent<CreateOrderEvent<T>> orderEvent = parseOrderEvent(data, eventType, materialType);
        OrderEntity orderEntity = createOrderEntity(orderEvent);
        saveAndProcessOrder(orderEvent, orderEntity, true);
    }

    private <T extends BaseEvent> void processUpdateEvent(String data, String eventType, String materialType) throws JsonProcessingException {
        OrderEvent<CreateOrderEvent<T>> orderEvent = parseOrderEvent(data, eventType, materialType);
        orderEvent.getEvent().setEventType(OrderEventType.ORDER_UPDATED);
        OrderEntity newOrderEntity = createOrderEntity(orderEvent);
        updateExistingOrder(newOrderEntity);
        saveAndProcessOrder(orderEvent, newOrderEntity, false);
    }

    private <T extends BaseEvent> OrderEvent<CreateOrderEvent<T>> parseOrderEvent(String data, String eventType, String materialType) {
        Type eventTypeToken = eventTypeUtils.getTypeEvents().get(eventType).get(materialType);
        return gson.fromJson(data, eventTypeToken);
    }

    private <T extends BaseEvent> OrderEntity createOrderEntity(OrderEvent<CreateOrderEvent<T>> orderEvent) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
        orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
        return orderEntity;
    }

    private void updateExistingOrder(OrderEntity newOrderEntity) {
        orderRepository.findById(newOrderEntity.getId()).ifPresentOrElse(
                existingOrder -> {
                    orderRepository.save(newOrderEntity);
                },
                () -> { throw new OrderNotFoundException("Order with id: " + newOrderEntity.getId() + " does not exist"); }
        );
    }

    private <T extends BaseEvent> void saveAndProcessOrder(OrderEvent<CreateOrderEvent<T>> orderEvent, OrderEntity orderEntity, boolean isNewOrder) {
        saveEvent(orderEvent);
        processEntity(orderEntity);
        sendNotification(orderEvent, isNewOrder);
    }

    private <T extends BaseEvent> void sendNotification(OrderEvent<CreateOrderEvent<T>> orderEvent, boolean isNewOrder) {
        CompletableFuture.runAsync(() -> sendEmail(orderEvent, isNewOrder), executor)
                .orTimeout(10, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    shutdownExecutor();
                    return null;
                });
    }

    @PreDestroy
    public void shutdownExecutor() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
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
        return Arrays.stream(MaterialType.values())
                .filter(material -> data.contains(material.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown material type in data: " + data))
                .name();
    }

    @Transactional
    public void processConstructionSiteEvent(String data, String eventType) throws InvocationTargetException, IllegalAccessException {
        eventTypeUtils.getConstructionMethodProcessors().get(eventType).invoke(this, data);
    }

    private void processCreateConstructionSite(String data) {
        OrderEvent<ConstructionSiteEvent> constructionEvent = parseConstructionSiteEvent(data);
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntityFromEvent(constructionEvent.getEvent());
        constructionSiteEntity.setId(String.valueOf(constructionEvent.getEvent().getId()));
        saveEvent(constructionEvent);
        constructionSiteService.saveConstructionSite(constructionSiteEntity);
    }

    private void processUpdateConstructionSite(String data) {
        OrderEvent<ConstructionSiteEvent> constructionEvent = parseConstructionSiteEvent(data);
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntityFromEvent(constructionEvent.getEvent());
        constructionSiteEntity.setId(String.valueOf(constructionEvent.getEvent().getId()));
        constructionSiteService.updateConstructionSite(constructionSiteEntity);
    }

    private OrderEvent<ConstructionSiteEvent> parseConstructionSiteEvent(String data) {
        Type eventTypeToken = new TypeToken<OrderEvent<ConstructionSiteEvent>>() {}.getType();
        return gson.fromJson(data, eventTypeToken);
    }

    private <T extends BaseEvent> void sendEmail(OrderEvent<CreateOrderEvent<T>> orderEvent, boolean isNewOrder) {
        EmailDTO emailDTO = mailMapper.toEmailDTO(orderEvent.getEvent());
        emailDTO.setNewOrder(isNewOrder);
        notificationServiceClient.sendNotification(emailDTO);
    }
}
