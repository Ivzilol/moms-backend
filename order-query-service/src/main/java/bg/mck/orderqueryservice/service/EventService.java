package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.utils.EventTypeMapper;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.mapper.FastenerMapper;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Arrays;

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


    public void processOrderEvent(String data, String eventType) {
        String materialType = getMaterialType(data);
        if (eventType.equals("ORDER_CREATED")) {
            if (materialType.equals("FASTENERS")) {
                OrderEvent<CreateOrderEvent<FasterEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("GALVANIZED_SHEET")) {
                OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("INSULATION")) {
                OrderEvent<CreateOrderEvent<InsulationEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("METAL")) {
                OrderEvent<CreateOrderEvent<MetalEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("PANELS")) {
                OrderEvent<CreateOrderEvent<PanelEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("REBAR")) {
                OrderEvent<CreateOrderEvent<RebarEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("SET")) {
                OrderEvent<CreateOrderEvent<SetEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("UNSPECIFIED")) {
                OrderEvent<CreateOrderEvent<UnspecifiedEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("SERVICE")) {
                OrderEvent<CreateOrderEvent<ServiceEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("TRANSPORT")) {
                OrderEvent<CreateOrderEvent<TransportEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                saveEvent(orderEvent);
                processEntity(orderEntity);
            } else {
                throw new IllegalArgumentException("Unknown material type: " + materialType);
            }

        }
    }

    private void processEntity(OrderEntity orderEntity) {
        orderService.saveOrder(orderEntity);
        redisService.cacheObject(orderEntity);
    }

    private <T extends BaseEvent> OrderEvent<T> saveEvent(OrderEvent<T> orderEvent) {
        return eventRepository.save(orderEvent);
    }

    private String getMaterialType(String data) {
        return Arrays.stream(MaterialType.values()).filter(material -> data.contains(material.name()))
                .findFirst().get().name();
    }
}
