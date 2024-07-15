package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.config.EventTypeMapper;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.BaseEvent;
import bg.mck.orderqueryservice.events.CreateOrderEvent;
import bg.mck.orderqueryservice.events.FasterEvent;
import bg.mck.orderqueryservice.events.OrderEvent;
import bg.mck.orderqueryservice.mapper.FastenerMapper;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final OrderRepository orderRepository;
    private final RedisService redisService;
    private final OrderMapper orderMapper;
    private final EventTypeMapper eventTypeMapper;
    private final Gson gson;
    private final OrderService orderService;
    private final FastenerMapper fastenerMapper;

    public EventService(EventRepository eventRepository, OrderRepository orderRepository, RedisService redisService, OrderMapper orderMapper, EventTypeMapper eventTypeMapper, Gson gson, OrderService orderService, FastenerMapper fastenerMapper) {
        this.eventRepository = eventRepository;
        this.orderRepository = orderRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeMapper = eventTypeMapper;
        this.gson = gson;
        this.orderService = orderService;
        this.fastenerMapper = fastenerMapper;
    }


//    public OrderEntity reconstructOrderEntity(Long orderId) {
//        List<OrderEvent<? extends BaseEvent>> events =
//                eventRepository.findByEventOrderIdOrderByEventLocalDateTimeAsc(orderId);
//
//
//        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setId(String.valueOf(orderId));
//
//
//        for (var event : events) {
//            applyEvent(event, orderEntity);
//        }
//
//        orderRepository.save(orderEntity);
//        redisService.cacheObject(orderEntity);
//
//        return orderEntity;
//    }

//    private void applyEvent(OrderEvent<? extends BaseEvent> orderEvent, OrderEntity orderEntity) {
//        BaseEvent event = orderEvent.getEvent();
//
//        if (event instanceof CreateOrderEvent newEvent) {
//            orderEntity.setOrderNumber(newEvent.getOrderNumber())
//        }
//    }

    public void processOrderEvent(String data, String eventType) {
        String materialType = getMaterialType(data);
        if (eventType.equals("ORDER_CREATED")) {
            switch (materialType) {
                case "FASTENERS":
                    OrderEvent<CreateOrderEvent<FasterEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                    saveEvent(orderEvent);
                    OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                    orderService.saveOrder(orderEntity);
                    redisService.cacheObject(orderEntity);
                    break;
            }
        }
    }

    private <T extends BaseEvent> OrderEvent<T> saveEvent(OrderEvent<T> orderEvent) {
        return eventRepository.save(orderEvent);
    }

//    private void processCreateOrderEvent(CreateOrderEvent orderEvent) {
//
//        OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent);
//        orderRepository.save(orderEntity);
//        redisService.cacheObject(orderEntity);
//
//    }

    private String getMaterialType(String data) {
        return Arrays.stream(MaterialType.values()).filter(material -> data.contains(material.name()))
                .findFirst().get().name();
    }
}
