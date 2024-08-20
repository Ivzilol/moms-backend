package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static bg.mck.ordercommandservice.testUtils.OrderUtil.createCreateOrderEvent;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    @Mock
    private MaterialService materialService;

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
        CreateOrderEvent<?> createOrderEvent = createCreateOrderEvent();

        when(orderMapper.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(orderMapper.toEvent(orderEntity)).thenReturn(createOrderEvent);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any(ConstructionSiteDTO.class))).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.of(3));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
//        doNothing().when(orderService).sendMaterialsToInventory(any(OrderEntity.class));

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
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CREATED)
                .orderId(1L)
                .orderNumber(4)
                .constructionSiteName("Site Name")
                .constructionSiteNumber("1234")
                .build());

        OrderConfirmationDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@abv.bg", null);
        verify(orderRepository).save(orderEntity);

        assertEquals(OrderStatus.CREATED, expectedCreateOrderDTO.getOrderStatus());
        assertNotEquals(OrderStatus.UPDATED, expectedCreateOrderDTO.getOrderStatus());
        assertEquals(1L, expectedCreateOrderDTO.getOrderId());
        assertEquals(4, expectedCreateOrderDTO.getOrderNumber());
        assertEquals("1234", expectedCreateOrderDTO.getConstructionSiteNumber());
        assertEquals("Site Name", expectedCreateOrderDTO.getConstructionSiteName());
    }

    @Test
    void test_CreateOrder_With_Fasteners_shouldReturn_correctData() {
        FastenerDTO fastener1 = MaterialUtil.createFastenerDTO();
        FastenerDTO fastener2 = MaterialUtil.createFastenerDTO();

        orderDTO.setConstructionSite(constructionSiteDTO)
                .setFasteners(List.of(fastener1, fastener2));
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CREATED)
                .orderId(1L)
                .orderNumber(4)
                .constructionSiteName("Site Name")
                .constructionSiteNumber("1234")
                .build());

        OrderConfirmationDTO expectedCreateOrderDTO = orderService.createOrder(orderDTO, "test@abv.bg", null);

        verify(orderRepository, times(1)).save(orderEntity);
        assertEquals(OrderStatus.CREATED, expectedCreateOrderDTO.getOrderStatus());
        assertEquals(1L, expectedCreateOrderDTO.getOrderId());
        assertEquals(4, expectedCreateOrderDTO.getOrderNumber());
        assertEquals("1234", expectedCreateOrderDTO.getConstructionSiteNumber());
        assertEquals("Site Name", expectedCreateOrderDTO.getConstructionSiteName());
    }

    @Test
    void deleteOrder_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
        // Arrange
        Long orderId = 1L;
        String email = "test@example.com";

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteOrder(orderId, email);
        });

        assertEquals("Order with id 1 not found", exception.getMessage());
        verify(orderRepository).findById(orderId);
        verify(orderRepository, never()).save(any(OrderEntity.class));
        verify(orderEventService, never()).createOrderEvent(any(OrderEntity.class));
    }


    @Test
    void updateOrder_ShouldReturnOrderConfirmation_WhenOrderIsUpdated() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();

        ConstructionSiteDTO constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setName("Site Name");
        orderDTO.setConstructionSite(constructionSiteDTO);

        String email = "test@example.com";
        List<MultipartFile> files = List.of();

        ConstructionSiteEntity constructionSite = new ConstructionSiteEntity();
        constructionSite.setName("Site Name")
                .setConstructionNumber("Site Number");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(100)
                .setConstructionSite(constructionSite)
                .setId(1L);

        when(constructionSiteService.getConstructionSiteByName(anyString())).thenReturn(constructionSite);
        when(orderMapper.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        // Instead of mocking return value, you check the interaction
        doAnswer(invocation -> {
            OrderEntity passedEntity = invocation.getArgument(0);
            assertEquals("Site Name", passedEntity.getConstructionSite().getName());
            return new OrderConfirmationDTO.Builder()
                    .orderStatus(OrderStatus.UPDATED)
                    .orderId(passedEntity.getId())
                    .orderNumber(passedEntity.getOrderNumber())
                    .constructionSiteName(passedEntity.getConstructionSite().getName())
                    .constructionSiteNumber(passedEntity.getConstructionSite().getConstructionNumber())
                    .build();
        }).when(orderEventService).createOrderEvent(any(OrderEntity.class));

        // Act
        OrderConfirmationDTO result = orderService.updateOrder(orderDTO, email, files);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getOrderId());
        assertEquals(100, result.getOrderNumber());
        verify(orderRepository).save(any(OrderEntity.class));
        verify(orderEventService).createOrderEvent(any(OrderEntity.class));
    }


    @Test
    void deleteOrder_ShouldReturnOrderConfirmation_WhenOrderIsDeleted() {
        // Arrange
        Long orderId = 1L;
        String email = "test@example.com";
        OrderEntity orderEntity = new OrderEntity();
        orderEntity
                .setOrderNumber(101)
                .setOrderStatus(OrderStatus.CREATED)
                .setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CANCELLED)
                .orderId(orderId)
                .orderNumber(101)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        // Act
        OrderConfirmationDTO result = orderService.deleteOrder(orderId, email);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.CANCELLED, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void restoreOrder_ShouldReturnOrderConfirmation_WhenOrderIsRestored() {
        // Arrange
        Long orderId = 1L;
        String email = "test@example.com";
        OrderEntity orderEntity = new OrderEntity();
        orderEntity
                .setOrderNumber(101)
                .setOrderStatus(OrderStatus.CANCELLED)
                .setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.PENDING)
                .orderId(orderId)
                .orderNumber(101)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        // Act
        OrderConfirmationDTO result = orderService.restoreOrder(orderId, email);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.PENDING, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void deleteMaterial_ShouldReturnOrderConfirmation_WhenMaterialIsDeleted() {
        // Arrange
        Long orderId = 1L;
        Long materialId = 2L;
        String email = "test@example.com";
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(101)
                .setMaterialType(MaterialType.METAL)
                .setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CREATED)
                .orderId(orderId)
                .orderNumber(101)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        // Act
        OrderConfirmationDTO result = orderService.deleteMaterial(orderId, materialId, email);

        // Assert
        assertNotNull(result);
        verify(materialService).deleteMaterial(materialId, "METAL");
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void updateOrderStatus_ShouldReturnOrderConfirmation_WhenStatusIsUpdated() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderStatus(OrderStatus.DELIVERY_IN_PROGRESS);

        // Assuming you're testing with METAL type materials
        orderDTO.setMaterialType(MaterialType.METAL);

        // Initialize materials and DTOs
        Set<MetalEntity> metals = new HashSet<>();
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId(1L);
        metalEntity.setAdminNote("Admin note");
        metals.add(metalEntity);

        List<MetalDTO> metalDTOs = new ArrayList<>();
        MetalDTO metalDTO = new MetalDTO();
        metalDTO.setId(1L);
        metalDTO.setAdminNote("##Admin note##New note");
        metalDTO.setMaterialStatus("APPROVED");
        metalDTOs.add(metalDTO);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity
                .setOrderStatus(OrderStatus.CREATED)
                .setMetals(metals)
                .setId(1L);

        // Set DTOs in the orderDTO
        orderDTO.setMetals(metalDTOs);

        when(orderRepository.findById(orderDTO.getId())).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.DELIVERY_IN_PROGRESS)
                .orderId(1L)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        // Act
        OrderConfirmationDTO result = orderService.updateOrderStatus(orderDTO, "Full Name");

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.DELIVERY_IN_PROGRESS, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }
}
