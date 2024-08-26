package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.EventData;
import bg.mck.ordercommandservice.event.EventType;
import bg.mck.ordercommandservice.event.TransportEvent;
import bg.mck.ordercommandservice.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventMapperServiceTest {

    @Mock
    private FastenerMapper fastenerMapper;
    @Mock
    private GalvanisedSheetMapper galvanisedSheetMapper;
    @Mock
    private MetalMapper metalMapper;
    @Mock
    private PanelMapper panelMapper;
    @Mock
    private RebarMapper rebarMapper;
    @Mock
    private ServiceMapper serviceMapper;
    @Mock
    private SetMapper setMapper;
    @Mock
    private TransportMapper transportMapper;
    @Mock
    private InsulationMapper insulationMapper;
    @Mock
    private UnspecifiedMapper unspecifiedMapper;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderQueryServiceClient orderQueryServiceClient;

    @InjectMocks
    private EventMapperService eventMapperService;


    @Test
    void mapEvent_withTransportMaterials_sendsEvent_OrderCreated() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(18L);
        orderEntity.setEmail("test@email.com");
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        orderEntity.setTransports(Set.of(new TransportEntity()));

        CreateOrderEvent<Object> createOrderEvent = new CreateOrderEvent<>();
        when(orderMapper.toEvent(orderEntity)).thenReturn(createOrderEvent);
        when(transportMapper.toEvent(any(TransportEntity.class))).thenReturn(new TransportEvent());

        // Act
        eventMapperService.mapEvent(orderEntity);

        // Assert
        ArgumentCaptor<EventData<CreateOrderEvent<Object>>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        verify(orderQueryServiceClient, times(1)).sendEvent(eventCaptor.capture(), eq(EventType.ORDER_CREATED.toString()));

        EventData<CreateOrderEvent<Object>> capturedEvent = eventCaptor.getValue();
        assertEquals(EventType.ORDER_CREATED, capturedEvent.getEventType());
        assertEquals(orderEntity.getId(), capturedEvent.getEvent().getOrderId());
        assertEquals(orderEntity.getEmail(), capturedEvent.getEvent().getEmail());
        assertEquals(1, capturedEvent.getEvent().getMaterials().size());
        verify(transportMapper, times(1)).toEvent(any(TransportEntity.class));
    }

    @Test
    void mapEvent_withTransportMaterials_sendsEvent() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(18L);
        orderEntity.setEmail("test@email.com");
        orderEntity.setOrderStatus(OrderStatus.COMPLETED);
        orderEntity.setTransports(Set.of(new TransportEntity()));

        CreateOrderEvent<Object> createOrderEvent = new CreateOrderEvent<>();
        when(orderMapper.toEvent(orderEntity)).thenReturn(createOrderEvent);
        when(transportMapper.toEvent(any(TransportEntity.class))).thenReturn(new TransportEvent());

        // Act
        eventMapperService.mapEvent(orderEntity);

        // Assert
        ArgumentCaptor<EventData<CreateOrderEvent<Object>>> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        verify(orderQueryServiceClient, times(1)).sendEvent(eventCaptor.capture(), eq(EventType.ORDER_UPDATED.toString()));

        EventData<CreateOrderEvent<Object>> capturedEvent = eventCaptor.getValue();
        assertEquals(EventType.ORDER_UPDATED, capturedEvent.getEventType());
        assertEquals(orderEntity.getId(), capturedEvent.getEvent().getOrderId());
        assertEquals(orderEntity.getEmail(), capturedEvent.getEvent().getEmail());
        assertEquals(1, capturedEvent.getEvent().getMaterials().size());
        verify(transportMapper, times(1)).toEvent(any(TransportEntity.class));
    }

    @Test
    void mapEvent_withNoMaterials_doesNotSendEvent() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderStatus.CREATED);

        eventMapperService.mapEvent(orderEntity);

        verify(orderQueryServiceClient, never()).sendEvent(any(), anyString());
    }
}

