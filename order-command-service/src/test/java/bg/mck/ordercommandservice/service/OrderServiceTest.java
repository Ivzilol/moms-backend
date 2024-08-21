package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil.createConstructionSiteDTO;
import static bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil.createConstructionSiteEntity;
import static bg.mck.ordercommandservice.testUtils.OrderUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ConstructionSiteService constructionSiteService;

    @Mock
    private MaterialService materialService;

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
    private OrderConfirmationDTO orderConfirmationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        orderDTO = createOrderDTO();
        orderEntity = createOrderEntity();
        constructionSiteEntity = createConstructionSiteEntity();
        constructionSiteDTO = createConstructionSiteDTO();
        orderConfirmationDTO = createOrderConfirmationDTO();
    }

    @Test
    void testCreateOrder_Success() {
        when(orderMapper.toOrderEntity(any(OrderDTO.class))).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any())).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.of(100));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(orderConfirmationDTO);

        OrderConfirmationDTO result = orderService.createOrder(orderDTO, "test@example.com", Collections.emptyList());

        assertNotNull(result);
        verify(orderMapper).toOrderEntity(orderDTO);
        verify(orderRepository).save(orderEntity);
        verify(inventoryService).sendMaterialsToInventory(orderDTO);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void testCreateOrder_NoPreviousOrderNumber() {
        when(orderMapper.toOrderEntity(any(OrderDTO.class))).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any())).thenReturn(constructionSiteEntity);
        when(orderRepository.findLastOrderNumber()).thenReturn(Optional.empty());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(orderConfirmationDTO);

        OrderConfirmationDTO result = orderService.createOrder(orderDTO, "test@example.com", Collections.emptyList());

        assertNotNull(result);
        verify(orderMapper).toOrderEntity(orderDTO);
        verify(orderRepository).save(orderEntity);
        verify(inventoryService).sendMaterialsToInventory(orderDTO);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void testCreateOrder_ExceptionDuringProcessing() {
        when(orderMapper.toOrderEntity(any(OrderDTO.class))).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByNumberAndName(any())).thenReturn(constructionSiteEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(orderDTO, "test@example.com", Collections.emptyList());
        });

        verify(orderMapper).toOrderEntity(orderDTO);
        verify(orderRepository).save(orderEntity);
        verify(inventoryService, never()).sendMaterialsToInventory(any(OrderDTO.class));
        verify(orderEventService, never()).createOrderEvent(any(OrderEntity.class));
    }

    @Test
    void testUpdateOrder_Success() {
        when(orderMapper.toOrderEntity(any(OrderDTO.class))).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByName(anyString())).thenReturn(constructionSiteEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(orderConfirmationDTO);

        OrderConfirmationDTO result = orderService.updateOrder(orderDTO, "test@example.com", Collections.emptyList());

        assertNotNull(result);
        verify(orderMapper).toOrderEntity(orderDTO);
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void testUpdateOrder_OrderNotFound() {
        when(orderMapper.toOrderEntity(any(OrderDTO.class))).thenReturn(orderEntity);
        when(constructionSiteService.getConstructionSiteByName(anyString())).thenReturn(constructionSiteEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenThrow(new OrderNotFoundException("Order not found"));

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrder(orderDTO, "test@example.com", Collections.emptyList());
        });

        verify(orderMapper).toOrderEntity(orderDTO);
        verify(orderRepository).save(orderEntity);
        verify(orderEventService, never()).createOrderEvent(any(OrderEntity.class));
    }

    @Test
    void deleteOrder_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
        Long orderId = 1L;
        String email = "test@example.com";
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

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

        OrderConfirmationDTO result = orderService.updateOrder(orderDTO, email, files);

        assertNotNull(result);
        assertEquals(1L, result.getOrderId());
        assertEquals(100, result.getOrderNumber());
        verify(orderRepository).save(any(OrderEntity.class));
        verify(orderEventService).createOrderEvent(any(OrderEntity.class));
    }


    @Test
    void deleteOrder_ShouldReturnOrderConfirmation_WhenOrderIsDeleted() {

        OrderEntity orderEntity = createOrderEntity();

        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CANCELLED)
                .orderId(orderEntity.getId())
                .orderNumber(101)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        OrderConfirmationDTO result = orderService.deleteOrder(orderEntity.getId(), orderEntity.getEmail());

        assertNotNull(result);
        assertEquals(OrderStatus.CANCELLED, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void restoreOrder_ShouldReturnOrderConfirmation_WhenOrderIsRestored() {
        // Arrange
        String email = "test@example.com";
        OrderEntity orderEntity = createOrderEntity();

        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.PENDING)
                .orderId(orderEntity.getId())
                .orderNumber(101)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        // Act
        OrderConfirmationDTO result = orderService.restoreOrder(orderEntity.getId(), email);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.PENDING, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void deleteMaterial_ShouldReturnOrderConfirmation_WhenMaterialIsDeleted() {
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

        OrderConfirmationDTO result = orderService.deleteMaterial(orderId, materialId, email);

        assertNotNull(result);
        verify(materialService).deleteMaterial(materialId, "METAL");
        verify(orderEventService).createOrderEvent(orderEntity);
    }

    @Test
    void updateOrderStatus_ShouldReturnOrderConfirmation_WhenStatusIsUpdated() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderStatus(OrderStatus.DELIVERY_IN_PROGRESS);

        orderDTO.setMaterialType(MaterialType.METAL);

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

        orderDTO.setMetals(metalDTOs);

        when(orderRepository.findById(orderDTO.getId())).thenReturn(Optional.of(orderEntity));

        OrderConfirmationDTO confirmationDTO = new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.DELIVERY_IN_PROGRESS)
                .orderId(1L)
                .build();

        when(orderEventService.createOrderEvent(any(OrderEntity.class))).thenReturn(confirmationDTO);

        OrderConfirmationDTO result = orderService.updateOrderStatus(orderDTO, "Full Name");

        assertNotNull(result);
        assertEquals(OrderStatus.DELIVERY_IN_PROGRESS, result.getOrderStatus());
        verify(orderRepository).save(orderEntity);
        verify(orderEventService).createOrderEvent(orderEntity);
    }
}
