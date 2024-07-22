package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.UpdateOrderDTO;
import bg.mck.orderqueryservice.entity.*;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import bg.mck.orderqueryservice.utils.EventTypeUtils;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RedisService redisService;
    private final OrderMapper orderMapper;
    private final EventTypeUtils eventTypeUtils;
    private final Gson gson;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public EventService(EventRepository eventRepository, RedisService redisService, OrderMapper orderMapper, EventTypeUtils eventTypeUtils, Gson gson, OrderService orderService, OrderRepository orderRepository) throws NoSuchMethodException {
        this.eventRepository = eventRepository;
        this.redisService = redisService;
        this.orderMapper = orderMapper;
        this.eventTypeUtils = eventTypeUtils;
        this.gson = gson;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
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

    public void updateOrder(UpdateOrderDTO updateOrderDTO, String eventType) {
        Integer orderNumber = Integer.parseInt(String.valueOf(updateOrderDTO.getOrderNumber()));
        Optional<OrderEntity> optionalOrderEntity = this.orderRepository.findByOrderNumber(orderNumber);
        OrderEntity orderEntity = optionalOrderEntity.get();

        switch (MaterialType.valueOf(updateOrderDTO.getMaterialType())) {
            case FASTENERS -> processingFasteners(updateOrderDTO, orderEntity);
            case GALVANIZED_SHEET -> processingGalvanizedSheet(updateOrderDTO, orderEntity);
            case INSULATION -> processingInsulation(updateOrderDTO, orderEntity);
            case METAL -> processingMetal(updateOrderDTO, orderEntity);
            case PANELS -> processingPanels(updateOrderDTO, orderEntity);
            case REBAR -> processingRebar(updateOrderDTO, orderEntity);
            case SERVICE -> processingService(updateOrderDTO, orderEntity);
            case SET -> processingSet(updateOrderDTO, orderEntity);
//            case TRANSPORT ->
//            case UNSPECIFIED ->
        }

        this.orderRepository.save(orderEntity);
    }

    private void processingSet(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<SetEntity> setEntities = orderEntity.getSets();
        for (SetEntity entity : setEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateSetEntity(entity, updateOrderDTO);
            }
        }
    }

    private void updateSetEntity(SetEntity entity, UpdateOrderDTO updateOrderDTO) {
        SetEvent setEvent = OrderMapper
                .INSTANCE.toUpdateSet(updateOrderDTO);
        OrderEvent<SetEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(setEvent);
        saveEvent(orderEvent);
        updateSet(entity, updateOrderDTO);
    }

    private void updateSet(SetEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateSetEntity(updateOrderDTO, entity);
    }

    private void processingRebar(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<RebarEntity> rebarEntities = orderEntity.getRebars();
        for (RebarEntity entity : rebarEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateRebarEntity(entity, updateOrderDTO);
            }
        }
    }

    private void updateRebarEntity(RebarEntity entity, UpdateOrderDTO updateOrderDTO) {
        RebarEvent rebarEvent = OrderMapper
                .INSTANCE.toUpdateRebar(updateOrderDTO);
        OrderEvent<RebarEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(rebarEvent);
        saveEvent(orderEvent);
        updateRebar(entity, updateOrderDTO);
    }

    private void updateRebar(RebarEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateRebarEntity(updateOrderDTO, entity);
    }

    private void processingService(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<ServiceEntity> serviceEntities = orderEntity.getServices();
        for (ServiceEntity entity : serviceEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateServiceEntity(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void updateServiceEntity(ServiceEntity entity, UpdateOrderDTO updateOrderDTO) {
        ServiceEvent serviceEvent = OrderMapper
                .INSTANCE.toUpdateService(updateOrderDTO);
        OrderEvent<ServiceEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(serviceEvent);
        saveEvent(orderEvent);
        updateService(entity, updateOrderDTO);
    }

    private void updateService(ServiceEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateServiceEntity(updateOrderDTO, entity);
    }

    private void processingPanels(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<PanelEntity> panelEntities = orderEntity.getPanels();
        for (PanelEntity entity : panelEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updatePanelEntity(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void updatePanelEntity(PanelEntity entity, UpdateOrderDTO updateOrderDTO) {
        PanelEvent panelEvent = OrderMapper
                .INSTANCE.toUpdatePanel(updateOrderDTO);
        OrderEvent<PanelEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(panelEvent);
        saveEvent(orderEvent);
        updatePanel(entity, updateOrderDTO);
    }

    private void updatePanel(PanelEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdatePanelEntity(updateOrderDTO, entity);
    }

    private void processingMetal(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<MetalEntity> metalEntities = orderEntity.getMetals();
        for (MetalEntity entity : metalEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateMetalEntity(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void updateMetalEntity(MetalEntity entity, UpdateOrderDTO updateOrderDTO) {
        MetalEvent metalEvent = OrderMapper
                .INSTANCE.toUpdateMetal(updateOrderDTO);
        OrderEvent<MetalEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(metalEvent);
        saveEvent(orderEvent);
        updateMetal(entity, updateOrderDTO);
    }

    private void updateMetal(MetalEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateMetalEntity(updateOrderDTO, entity);
    }

    private void processingInsulation(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<InsulationEntity> insulationEntities = orderEntity.getInsulation();
        for (InsulationEntity entity : insulationEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateInsulationEntity(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void updateInsulationEntity(InsulationEntity entity, UpdateOrderDTO updateOrderDTO) {
        InsulationEvent insulationEvent = OrderMapper
                .INSTANCE.toUpdateInsulation(updateOrderDTO);
        OrderEvent<InsulationEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(insulationEvent);
        saveEvent(orderEvent);
        updateInsulation(entity, updateOrderDTO);
    }

    private void updateInsulation(InsulationEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateInsulationEntity(updateOrderDTO, entity);
    }

    private void processingGalvanizedSheet(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<GalvanisedSheetEntity> galvanisedSheetEntities = orderEntity.getGalvanisedSheets();
        for (GalvanisedSheetEntity entity : galvanisedSheetEntities) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                updateGalvanisedSheetEntity(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void updateGalvanisedSheetEntity(GalvanisedSheetEntity entity, UpdateOrderDTO updateOrderDTO) {
        GalvanisedSheetEvent galvanisedSheetEvent = OrderMapper
                .INSTANCE.toUpdateGalvaniseSheet(updateOrderDTO);
        OrderEvent<GalvanisedSheetEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(galvanisedSheetEvent);
        saveEvent(orderEvent);
        updateGalvanisedEntity(entity, updateOrderDTO);
    }

    private void updateGalvanisedEntity(GalvanisedSheetEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateGalvanisedEntity(updateOrderDTO, entity);
    }

    private void processingFasteners(UpdateOrderDTO updateOrderDTO, OrderEntity orderEntity) {
        Set<FastenerEntity> fasteners = orderEntity.getFasteners();
        for (FastenerEntity entity : fasteners) {
            if (entity.getId().equals(updateOrderDTO.getId())) {
                saveUpdateEvent(entity, updateOrderDTO);
                break;
            }
        }
    }

    private void saveUpdateEvent(FastenerEntity entity, UpdateOrderDTO updateOrderDTO) {
        CreateUpdateOrderEvent createUpdateOrderEvent = OrderMapper
                .INSTANCE.toCreateUpdateOrderEvent(updateOrderDTO);
        OrderEvent<CreateUpdateOrderEvent> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(createUpdateOrderEvent);
        saveEvent(orderEvent);
        updateFastenerEntity(entity, updateOrderDTO);
    }

    private void updateFastenerEntity(FastenerEntity entity, UpdateOrderDTO updateOrderDTO) {
        orderMapper.toUpdateFasterEntity(updateOrderDTO, entity);
    }
}
