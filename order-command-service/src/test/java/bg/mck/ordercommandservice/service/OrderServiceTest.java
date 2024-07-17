package bg.mck.ordercommandservice.service;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.service.ConstructionSiteService;
import bg.mck.ordercommandservice.service.OrderService;
import bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import bg.mck.ordercommandservice.testUtils.OrderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @InjectMocks
    private OrderService orderService;

    private OrderDTO orderDTO;
    private OrderEntity orderEntity;
    private ConstructionSiteEntity constructionSiteEntity;
    private ConstructionSiteDTO constructionSiteDTO;

    @BeforeEach
    public void setUp() {
        constructionSiteEntity = ConstructionSiteUtil.createConstructionSiteEntity();
        constructionSiteDTO = ConstructionSiteUtil.createConstructionSiteDTO();
        orderDTO = OrderUtil.createOrderDTO();
        orderEntity = OrderUtil.createOrderEntity();

        when(orderMapper.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any(ConstructionSiteDTO.class))).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.of(3));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        reset(orderRepository, constructionSiteService, orderMapper);
    }

    @Test
    void test_CreateOrder_shouldReturn_correctData() {
        CreateOrderDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@example.com");
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
                .setFasteners(Set.of(fastener1, fastener2));

        CreateOrderDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@example.com");

        verify(orderRepository, times(1)).save(orderEntity);
        assertEquals(OrderStatus.CREATED, expectedCreateOrderDTO.getOrderStatus());
        assertEquals(1L, expectedCreateOrderDTO.getOrderId());
        assertEquals(4, expectedCreateOrderDTO.getOrderNumber());
        assertEquals("1234", expectedCreateOrderDTO.getConstructionSiteNumber());
        assertEquals("Site Name", expectedCreateOrderDTO.getConstructionSiteName());
    }
}
