package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.BaseEvent;
import bg.mck.orderqueryservice.events.CreateOrderEvent;
import bg.mck.orderqueryservice.events.OrderEvent;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.utils.EventTypeUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

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

    public EventService(EventRepository eventRepository, RedisService redisService, OrderMapper orderMapper, EventTypeUtils eventTypeUtils, Gson gson, OrderService orderService) throws NoSuchMethodException {
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeUtils = eventTypeUtils;
        this.gson = gson;
        this.orderService = orderService;
    }


    public void processOrderEvent(String data, String eventType) throws InvocationTargetException, IllegalAccessException {
        String materialType = getMaterialType(data);
        eventTypeUtils.getMethodProcessors().get(eventType).invoke(this, data, eventType, materialType);
    }

    private <T extends BaseEvent> void processCreateEvent(String data, String eventType, String materialType) {
        Type eventTypeToken = eventTypeUtils.getTypeEvents().get(eventType).get(materialType);
        OrderEvent<CreateOrderEvent<T>> orderEvent = gson.fromJson(data, eventTypeToken);
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
        orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
        saveEvent(orderEvent);
        processEntity(orderEntity);
    }

    private <T extends BaseEvent> void processUpdateEvent(String data, String eventType, String materialType) {
//        Type eventTypeToken = eventTypeMapper.getTypeEvents().get(eventType).get(materialType);
//        OrderEvent<UpdateOrderEvent<T>> orderEvent = gson.fromJson(data, eventTypeToken);
//        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
//        orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
//        saveEvent(orderEvent);
//        processEntity(orderEntity);
    }

    private <T extends BaseEvent> void processDeleteEvent(String data, String eventType, String materialType) {
//        Type eventTypeToken = eventTypeMapper.getTypeEvents().get(eventType).get(materialType);
//        OrderEvent<DeleteOrderEvent<T>> orderEvent = gson.fromJson(data, eventTypeToken);
//        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
//        orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
//        saveEvent(orderEvent);
//        processEntity(orderEntity);
    }

    private void processEntity(OrderEntity orderEntity) {
        orderService.saveOrder(orderEntity);
        redisService.cacheObject(orderEntity);
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
}
