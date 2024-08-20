package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GPTOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ConstructionSiteService constructionSiteService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private MaterialService materialService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private OrderEventService orderEventService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

