package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OrderEventServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EventMapperService eventMapperService;

    @InjectMocks
    private OrderEventService orderEventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidOrder_whenCreateOrderEvent_thenReturnOrderConfirmationDTO() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderStatus(OrderStatus.COMPLETED);
        orderEntity.setOrderNumber(123);

        ConstructionSiteEntity constructionSite = new ConstructionSiteEntity();
        constructionSite.setName("Site Name");
        constructionSite.setConstructionNumber("Site Number");
        orderEntity.setConstructionSite(constructionSite);

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(orderEntity));

        // Act
        OrderConfirmationDTO result = orderEventService.createOrderEvent(orderEntity);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.COMPLETED, result.getOrderStatus());
        assertEquals(1L, result.getOrderId());
        assertEquals(123, result.getOrderNumber());
        assertEquals("Site Name", result.getConstructionSiteName());
        assertEquals("Site Number", result.getConstructionSiteNumber());

        verify(orderRepository).findById(1L);
        verify(eventMapperService).mapEvent(orderEntity);
    }

    @Test
    void givenInvalidOrder_whenCreateOrderEvent_thenThrowEntityNotFoundException() {
        // Arrange
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            orderEventService.createOrderEvent(orderEntity);
        });

        assertEquals("Order not found", exception.getMessage());

        verify(orderRepository).findById(1L);
        verify(eventMapperService, never()).mapEvent(any());
    }
}

