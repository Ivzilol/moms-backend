package bg.mck.ordercommandservice.unitTests;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.mapper.*;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.service.ConstructionSiteService;
import bg.mck.ordercommandservice.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderCreationTests {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ConstructionSiteService constructionSiteService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderQueryServiceClient orderQueryServiceClient;
    @Mock
    private FastenerMapper fastenerMapper;
    @Mock
    private GalvanisedSheetMapper galvanisedSheetMapper;
    @Mock
    private InsulationMapper insulationMapper;
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
    private UnspecifiedMapper unspecifiedMapper;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private Logger logger;

    private OrderDTO orderDTO;
    private OrderEntity orderEntity;
    private ConstructionSiteEntity constructionSiteEntity;
    private CreateOrderDTO expectedCreateOrderDTO;

    @BeforeEach
    public void setUp() {
        constructionSiteEntity = new ConstructionSiteEntity();
        orderDTO = new OrderDTO();
//        orderDTO.setConstructionSite();
        orderEntity = new OrderEntity();

        expectedCreateOrderDTO = new CreateOrderDTO.Builder()
                .orderId(1L)
                .orderNumber(2)
                .constructionSiteName("Site Name")
                .constructionSiteNumber("1234")
                .build();

        // Mock the behavior of the dependencies
        when(orderMapper.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByNumberAndName(orderDTO.getConstructionSite())).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.of(3));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

    }
    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        reset(orderRepository, constructionSiteService, orderMapper, logger);
    }

    @Test
    void testCreateOrder() {
        CreateOrderDTO result = orderService.createOrder(orderDTO, "test@example.com");

        // Verify that the orderRepository.save method was called
        verify(orderRepository).save(orderEntity);

        // Verify the properties of the saved OrderEntity
        assertEquals("test@example.com", orderEntity.getEmail());
        assertEquals(OrderStatus.CREATED, orderEntity.getOrderStatus());
        assertEquals(4, orderEntity.getOrderNumber());
        assertEquals(constructionSiteEntity, orderEntity.getConstructionSite());

        // Verify the properties of the returned CreateOrderDTO
//        assertEquals(expectedCreateOrderDTO.getOrderId(), result.getOrderId());
//        assertEquals(expectedCreateOrderDTO.getOrderNumber(), result.getOrderNumber());
//        assertEquals(expectedCreateOrderDTO.getConstructionSiteName(), result.getConstructionSiteName());
//        assertEquals(expectedCreateOrderDTO.getConstructionSiteNumber(), result.getConstructionSiteNumber());

        // Verify that the logger.info method was called
//        verify(logger).info(eq("Order with id {} created successfully"), eq(orderEntity.getId()));
    }
}
