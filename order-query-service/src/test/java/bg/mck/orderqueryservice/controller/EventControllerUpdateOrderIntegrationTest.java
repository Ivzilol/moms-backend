package bg.mck.orderqueryservice.controller;

import bg.mck.orderqueryservice.client.NotificationServiceClient;
import bg.mck.orderqueryservice.entity.*;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.repository.ConstructionSiteRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import bg.mck.orderqueryservice.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerUpdateOrderIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    private Integer id = 1;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private NotificationServiceClient notificationServiceClient;

    private OrderEntity order;

    private ConstructionSiteEntity constructionSiteEntity;

    @MockBean
    private RedisService redisService;

    @Autowired
    private ConstructionSiteRepository constructionSiteRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        constructionSiteRepository.deleteAll();
        constructionSiteEntity = new ConstructionSiteEntity()
                .setConstructionNumber("1.10.18")
                .setName("Kaufland");
        constructionSiteRepository.save(constructionSiteEntity);

        when(notificationServiceClient.sendNotification(any())).thenReturn(null);
        doNothing().when(redisService).cacheOrder(any());
    }

    private OrderEntity createInitialOrder() {
        OrderEntity entity = new OrderEntity();
        entity.setId(String.valueOf(id));
        entity.setOrderStatus(OrderStatus.CREATED);
        entity.setConstructionSite(constructionSiteEntity);
        entity.setOrderNumber(54321);
        return orderRepository.save(entity);
    }

    @Test
    void testOrderUpdatedWithFastenerEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Fastener material in the update
        CreateOrderEvent<FasterEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.FASTENERS);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new FasterEvent().setId(1L).setType("Fastener")));
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        updateOrderEvent.setOrderNumber(54321);
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId(String.valueOf(id));
        fastenerEntity.setType("Fastener");
        Set<FastenerEntity> fastenerEntitySet = Set.of(fastenerEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getFasteners(), fastenerEntitySet);
    }

    @Test
    void testOrderUpdatedWithGalvanizedSheetEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Galvanized Sheet material in the update
        CreateOrderEvent<GalvanisedSheetEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.GALVANIZED_SHEET);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new GalvanisedSheetEvent().setId(id.longValue()).setType("GalvanizedSheet")));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
        galvanisedSheetEntity.setId(String.valueOf(id));
        galvanisedSheetEntity.setType("GalvanizedSheet");
        Set<GalvanisedSheetEntity> galvanizedSheetEntitySet = Set.of(galvanisedSheetEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getGalvanisedSheets(), galvanizedSheetEntitySet);
    }

    @Test
    void testOrderUpdatedWithInsulationEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add an Insulation material in the update
        CreateOrderEvent<InsulationEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.INSULATION);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new InsulationEvent().setId(id.longValue()).setType("Insulation")));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity.setId(String.valueOf(id));
        insulationEntity.setType("Insulation");
        Set<InsulationEntity> insulationEntitySet = Set.of(insulationEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getInsulation(), insulationEntitySet);
    }

    @Test
    void testOrderUpdatedWithMetalEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Metal material in the update
        CreateOrderEvent<MetalEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.METAL);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new MetalEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId(String.valueOf(id));
        Set<MetalEntity> metalEntitySet = Set.of(metalEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getMetals(), metalEntitySet);
    }

    @Test
    void testOrderUpdatedWithPanelEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Panel material in the update
        CreateOrderEvent<PanelEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.PANELS);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new PanelEvent().setId(id.longValue()).setType("Panel")));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setId(String.valueOf(id));
        panelEntity.setType("Panel");
        Set<PanelEntity> panelEntitySet = Set.of(panelEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getPanels(), panelEntitySet);
    }

    @Test
    void testOrderUpdatedWithRebarEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Rebar material in the update
        CreateOrderEvent<RebarEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.REBAR);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new RebarEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity.setId(String.valueOf(id));
        Set<RebarEntity> rebarEntitySet = Set.of(rebarEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getRebars(), rebarEntitySet);
    }

    @Test
    void testOrderUpdatedWithSetEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Set material in the update
        CreateOrderEvent<SetEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.SET);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new SetEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        SetEntity setEntity = new SetEntity();
        setEntity.setId(String.valueOf(id));
        Set<SetEntity> setEntitySet = Set.of(setEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getSets(), setEntitySet);
    }

    @Test
    void testOrderUpdatedWithServiceEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Service material in the update
        CreateOrderEvent<ServiceEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.SERVICE);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new ServiceEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(String.valueOf(id));
        Set<ServiceEntity> serviceEntitySet = Set.of(serviceEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getServices(), serviceEntitySet);
    }

    @Test
    void testOrderUpdatedWithTransportEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add a Transport material in the update
        CreateOrderEvent<TransportEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.TRANSPORT);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new TransportEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        TransportEntity transportEntity = new TransportEntity();
        transportEntity.setId(String.valueOf(id));
        Set<TransportEntity> transportEntitySet = Set.of(transportEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getTransports(), transportEntitySet);
    }

    @Test
    void testOrderUpdatedWithUnspecifiedEvent() throws Exception {
        // Create initial order
        order = createInitialOrder();

        // Add an Unspecified material in the update
        CreateOrderEvent<UnspecifiedEvent> updateOrderEvent = new CreateOrderEvent<>();
        updateOrderEvent.setOrderId(id.longValue());
        updateOrderEvent.setOrderStatus(OrderStatus.UPDATED);
        updateOrderEvent.setMaterialType(MaterialType.UNSPECIFIED);
        updateOrderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        updateOrderEvent.setMaterials(Set.of(new UnspecifiedEvent().setId(id.longValue())));
        updateOrderEvent.setOrderNumber(54321);
        updateOrderEvent.setConstructionSite(constructionSiteEntity);
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId(String.valueOf(id));
        Set<UnspecifiedEntity> unspecifiedEntitySet = Set.of(unspecifiedEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_UPDATED);
        orderEvent.setEvent(updateOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);

        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_UPDATED")
                        .content(data))
                .andExpect(status().isOk());

        // Verify the update
        OrderEntity updatedOrder = orderRepository.findById(String.valueOf(id)).get();
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getOrderNumber(), updateOrderEvent.getOrderNumber());
        assertEquals(updatedOrder.getUnspecified(), unspecifiedEntitySet);
    }
}
