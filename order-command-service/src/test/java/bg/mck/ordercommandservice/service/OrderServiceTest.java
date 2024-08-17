package bg.mck.ordercommandservice.service;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import bg.mck.ordercommandservice.testUtils.OrderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static bg.mck.ordercommandservice.testUtils.OrderUtil.createCreateOrderEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ConstructionSiteService constructionSiteService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private FastenerMapper fastenerMapper;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private OrderEventService orderEventService;
    @InjectMocks
    private OrderService orderService;

    private OrderDTO orderDTO;
    private OrderEntity orderEntity;
    private ConstructionSiteEntity constructionSiteEntity;
    private ConstructionSiteDTO constructionSiteDTO;

    @BeforeEach
    public void setUp() {
        constructionSiteEntity = ConstructionSiteUtil.createConstructionSiteEntityWithID();
        constructionSiteDTO = ConstructionSiteUtil.createConstructionSiteDTO();
        orderDTO = OrderUtil.createOrderDTO();
        orderEntity = OrderUtil.createOrderEntity();
        CreateOrderEvent<FasterEvent> createOrderEvent = createCreateOrderEvent();

        when(orderMapper.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(orderMapper.toEvent(orderEntity)).thenReturn(createOrderEvent);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any(ConstructionSiteDTO.class))).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.of(3));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
//        doNothing().when(orderService).sendMaterialsToInventory(any(OrderEntity.class));
        doNothing().when(inventoryService).sendMaterialsToInventory(any(OrderDTO.class));
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CREATED)
                .orderId(1L)
                .orderNumber(4)
                .constructionSiteName("Site Name")
                .constructionSiteNumber("1234")
                .build());
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        reset(orderRepository, constructionSiteService, orderMapper);
    }

    @Test
    void test_CreateOrder_shouldReturn_correctData() {
        FastenerDTO fastener1 = MaterialUtil.createFastenerDTO();
        FastenerDTO fastener2 = MaterialUtil.createFastenerDTO();

        orderDTO.setFasteners(List.of(fastener1, fastener2));

        OrderConfirmationDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@abv.bg", null);
        verify(orderRepository).save(orderEntity);

        assertEquals(OrderStatus.CREATED, expectedCreateOrderDTO.getOrderStatus());
        assertEquals(1L, expectedCreateOrderDTO.getOrderId());
        assertEquals(4, expectedCreateOrderDTO.getOrderNumber());
        assertEquals("1234", expectedCreateOrderDTO.getConstructionSiteNumber());
        assertEquals("Site Name", expectedCreateOrderDTO.getConstructionSiteName());
    }

    @Test
    void testCreateOrderWithFasteners() {
        FastenerDTO fastener1 = MaterialUtil.createFastenerDTO();
        FastenerDTO fastener2 = MaterialUtil.createFastenerDTO();

        orderDTO.setConstructionSite(constructionSiteDTO)
                .setFasteners(List.of(fastener1, fastener2));

        OrderConfirmationDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@abv.bg", null);

        verify(orderRepository, times(1)).save(orderEntity);
        assertEquals(OrderStatus.CREATED, expectedCreateOrderDTO.getOrderStatus());
        assertEquals(1L, expectedCreateOrderDTO.getOrderId());
        assertEquals(4, expectedCreateOrderDTO.getOrderNumber());
        assertEquals("1234", expectedCreateOrderDTO.getConstructionSiteNumber());
        assertEquals("Site Name", expectedCreateOrderDTO.getConstructionSiteName());
    }
}
