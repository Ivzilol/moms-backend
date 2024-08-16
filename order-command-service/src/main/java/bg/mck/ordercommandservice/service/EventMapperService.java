package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.EventData;
import bg.mck.ordercommandservice.event.EventType;
import bg.mck.ordercommandservice.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventMapperService {

    private final FastenerMapper fastenerMapper;
    private final GalvanisedSheetMapper galvanisedSheetMapper;
    private final MetalMapper metalMapper;
    private final PanelMapper panelMapper;
    private final RebarMapper rebarMapper;
    private final ServiceMapper serviceMapper;
    private final SetMapper setMapper;
    private final TransportMapper transportMapper;
    private final InsulationMapper insulationMapper;
    private final UnspecifiedMapper unspecifiedMapper;
    private final OrderMapper orderMapper;
    private final OrderQueryServiceClient orderQueryServiceClient;

    public EventMapperService(FastenerMapper fastenerMapper, GalvanisedSheetMapper galvanisedSheetMapper, MetalMapper metalMapper, PanelMapper panelMapper, RebarMapper rebarMapper, ServiceMapper serviceMapper, SetMapper setMapper, TransportMapper transportMapper, InsulationMapper insulationMapper, UnspecifiedMapper unspecifiedMapper, OrderMapper orderMapper, OrderQueryServiceClient orderQueryServiceClient) {
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
        this.metalMapper = metalMapper;
        this.panelMapper = panelMapper;
        this.rebarMapper = rebarMapper;
        this.serviceMapper = serviceMapper;
        this.setMapper = setMapper;
        this.transportMapper = transportMapper;
        this.insulationMapper = insulationMapper;
        this.unspecifiedMapper = unspecifiedMapper;
        this.orderMapper = orderMapper;
        this.orderQueryServiceClient = orderQueryServiceClient;
    }

    public void mapEvent(OrderEntity orderEntity) {
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

        EventData<CreateOrderEvent<E>> orderEvent = new EventData<>();

        orderEvent.setEventType(orderEntity.getOrderStatus() != OrderStatus.CREATED ? EventType.ORDER_UPDATED : EventType.ORDER_CREATED);

        CreateOrderEvent<E> createOrderEvent = orderMapper.toEvent(orderEntity);
        createOrderEvent.setOrderId(orderEntity.getId());
        createOrderEvent.setEventType(orderEvent.getEventType());
        createOrderEvent.setEventTime(LocalDateTime.now());
        createOrderEvent.setMaterials(materialEvents);
        createOrderEvent.setEmail(orderEntity.getEmail());
        orderEvent.setEvent(createOrderEvent);

        orderQueryServiceClient.sendEvent(orderEvent, orderEvent.getEventType().toString());
    }
}

