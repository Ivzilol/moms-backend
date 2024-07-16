package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.utils.EventTypeMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RedisService redisService;
    private final OrderMapper orderMapper;
    private final EventTypeMapper eventTypeMapper;
    private final Gson gson;
    private final OrderService orderService;

    public EventService(EventRepository eventRepository, RedisService redisService, OrderMapper orderMapper, EventTypeMapper eventTypeMapper, Gson gson, OrderService orderService) {
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeMapper = eventTypeMapper;
        this.gson = gson;
        this.orderService = orderService;
    }


    public void processOrderEvent(String data, String eventType) {
        String materialType = getMaterialType(data);
        if (eventType.equals("ORDER_CREATED")) {
            if (materialType.equals("FASTENERS")) {
                OrderEvent<CreateOrderEvent<FasterEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("GALVANIZED_SHEET")) {
                OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("INSULATION")) {
                OrderEvent<CreateOrderEvent<InsulationEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("METAL")) {
                OrderEvent<CreateOrderEvent<MetalEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("PANELS")) {
                OrderEvent<CreateOrderEvent<PanelEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("REBAR")) {
                OrderEvent<CreateOrderEvent<RebarEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("SET")) {
                OrderEvent<CreateOrderEvent<SetEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("UNSPECIFIED")) {
                OrderEvent<CreateOrderEvent<UnspecifiedEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("SERVICE")) {
                OrderEvent<CreateOrderEvent<ServiceEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
                saveEvent(orderEvent);
                processEntity(orderEntity);

            } else if (materialType.equals("TRANSPORT")) {
                OrderEvent<CreateOrderEvent<TransportEvent>> orderEvent = gson.fromJson(data, eventTypeMapper.getTypeEvents().get(eventType).get(materialType));
                OrderEntity orderEntity = orderMapper.toOrderEntity(orderEvent.getEvent());
                orderEntity.setId(String.valueOf(orderEvent.getEvent().getOrderId()));
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
