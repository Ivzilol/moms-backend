package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.exception.OrderNotFoundException;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity;
    private OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderEntity = new OrderEntity();
        orderEntity.setId("1");
        orderEntity.setEmail("test@test.com");
        orderEntity.setOrderNumber(12345);

        orderDTO = new OrderDTO();
        orderDTO.setId("1");
        orderDTO.setOrderNumber(12345);
        orderDTO.setOrderDescription("Test Order");
    }

    @Test
    void testSaveOrder() {
        orderService.saveOrder(orderEntity);
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void testGetAllOrders_CacheHit() {

        List<OrderDTO> result = orderService.getAllOrders();
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    void testGetAllOrders_CacheMiss() {
        when(orderRepository.findAll()).thenReturn(List.of(orderEntity));
        when(orderMapper.fromOrderEntityToDTO(orderEntity)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getAllOrders();
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));

    }

    @Test
    void testGetAllOrders_CacheException() {
        when(orderRepository.findAll()).thenReturn(List.of(orderEntity));
        when(orderMapper.fromOrderEntityToDTO(orderEntity)).thenReturn(orderDTO);

        assertThrows(RuntimeException.class, () -> orderService.getAllOrders());
    }

    @Test
    void testGetMyOrders() {
        when(orderRepository.findByEmail("test@test.com")).thenReturn(List.of(orderEntity));
        when(orderMapper.fromOrderEntityToDTO(orderEntity)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getMyOrders("test@test.com");
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));

        verify(orderRepository, times(1)).findByEmail("test@test.com");
    }

    @Test
    void testGetOrderById_FoundInCache() {

        OrderDTO result = orderService.getOrderById(1L);
        assertEquals(orderDTO, result);

        verify(orderRepository, times(0)).findById("1");
    }

    @Test
    void testGetOrderById_FoundInDatabase() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(orderEntity));
        when(orderMapper.fromOrderEntityToDTO(orderEntity)).thenReturn(orderDTO);

        OrderDTO result = orderService.getOrderById(1L);
        assertEquals(orderDTO, result);

    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1L));
    }
}