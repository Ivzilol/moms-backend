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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerCreateOrderIntegrationTest {


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

    @Test
    void testOrderCreatedWithFasterEvent() throws Exception {
        CreateOrderEvent<FasterEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.FASTENERS);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new FasterEvent().setId(1L).setType("Fastener")));
        createOrderEvent.setOrderNumber(12345);
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId(String.valueOf(id));
        fastenerEntity.setType("Fastener");
        Set<FastenerEntity> fastenerEntity1 = Set.of(fastenerEntity);
        order = getEntityToCheck("CREATED", "FASTENERS", fastenerEntity1);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getFasteners(), order.getFasteners());

    }

    @Test
    void testOrderCreatedWithGalvanizedSheetEvent() throws Exception {
        CreateOrderEvent<GalvanisedSheetEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.GALVANIZED_SHEET);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new GalvanisedSheetEvent().setId(id.longValue()).setType("GalvanizedSheet")));
        createOrderEvent.setOrderNumber(12345);
        GalvanisedSheetEntity galvanizedSheetEntity = new GalvanisedSheetEntity();
        galvanizedSheetEntity.setId(String.valueOf(id));
        galvanizedSheetEntity.setType("GalvanizedSheet");
        Set<GalvanisedSheetEntity> galvanizedSheetEntitySet = Set.of(galvanizedSheetEntity);
        order = getEntityToCheck("CREATED", "GALVANIZED_SHEET", galvanizedSheetEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getGalvanisedSheets(), order.getGalvanisedSheets());
    }

    @Test
    void testOrderCreatedWithInsulationEvent() throws Exception {
        CreateOrderEvent<InsulationEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.INSULATION);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new InsulationEvent().setId(id.longValue()).setType("Insulation")));
        createOrderEvent.setOrderNumber(12345);
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity.setId(String.valueOf(id));
        insulationEntity.setType("Insulation");
        Set<InsulationEntity> insulationEntitySet = Set.of(insulationEntity);
        order = getEntityToCheck("CREATED", "INSULATION", insulationEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getInsulation(), order.getInsulation());
    }

    @Test
    void testOrderCreatedWithMetalEvent() throws Exception {
        CreateOrderEvent<MetalEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.METAL);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new MetalEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId(String.valueOf(id));
        Set<MetalEntity> metalEntitySet = Set.of(metalEntity);
        order = getEntityToCheck("CREATED", "METAL", metalEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getMetals(), order.getMetals());
    }

    @Test
    void testOrderCreatedWithPanelEvent() throws Exception {
        CreateOrderEvent<PanelEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.PANELS);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new PanelEvent().setId(id.longValue()).setType("Panel")));
        createOrderEvent.setOrderNumber(12345);
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setId(String.valueOf(id));
        panelEntity.setType("Panel");
        Set<PanelEntity> panelEntitySet = Set.of(panelEntity);
        order = getEntityToCheck("CREATED", "PANELS", panelEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getPanels(), order.getPanels());
    }

    @Test
    void testOrderCreatedWithRebarEvent() throws Exception {
        CreateOrderEvent<RebarEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.REBAR);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new RebarEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity.setId(String.valueOf(id));
        Set<RebarEntity> rebarEntitySet = Set.of(rebarEntity);
        order = getEntityToCheck("CREATED", "REBAR", rebarEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getRebars(), order.getRebars());
    }

    @Test
    void testOrderCreatedWithSetEvent() throws Exception {
        CreateOrderEvent<SetEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.SET);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new SetEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        SetEntity setEntity = new SetEntity();
        setEntity.setId(String.valueOf(id));
        Set<SetEntity> setEntitySet = Set.of(setEntity);
        order = getEntityToCheck("CREATED", "SET", setEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getSets(), order.getSets());
    }

    @Test
    void testOrderCreatedWithServiceEvent() throws Exception {
        CreateOrderEvent<ServiceEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.SERVICE);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new ServiceEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(String.valueOf(id));
        Set<ServiceEntity> serviceEntitySet = Set.of(serviceEntity);
        order = getEntityToCheck("CREATED", "SERVICE", serviceEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getServices(), order.getServices());
    }

    @Test
    void testOrderCreatedWithTransportEvent() throws Exception {
        CreateOrderEvent<TransportEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.TRANSPORT);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new TransportEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        TransportEntity transportEntity = new TransportEntity();
        transportEntity.setId(String.valueOf(id));
        Set<TransportEntity> transportEntitySet = Set.of(transportEntity);
        order = getEntityToCheck("CREATED", "TRANSPORT", transportEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getTransports(), order.getTransports());
    }

    @Test
    void testOrderCreatedWithUnspecifiedEvent() throws Exception {
        CreateOrderEvent<UnspecifiedEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(id.longValue());
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.UNSPECIFIED);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new UnspecifiedEvent().setId(id.longValue())));
        createOrderEvent.setOrderNumber(12345);
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId(String.valueOf(id));
        Set<UnspecifiedEntity> unspecifiedEntitySet = Set.of(unspecifiedEntity);
        order = getEntityToCheck("CREATED", "UNSPECIFIED", unspecifiedEntitySet);
        createOrderEvent.setConstructionSite(constructionSiteEntity);

        OrderEvent<CreateOrderEvent<?>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setEvent(createOrderEvent);
        String data = objectMapper.writeValueAsString(orderEvent);
        mockMvc.perform(post("/orders/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Event-Type", "ORDER_CREATED")
                        .content(data))
                .andExpect(status().isOk());
        OrderEntity entity = orderRepository.findById(String.valueOf(id)).get();

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getUnspecified(), order.getUnspecified());
    }

    private OrderEntity getEntityToCheck(String orderStatus, String materialsType, Set materials) {
        OrderEntity entity = new OrderEntity();
        entity.setId(String.valueOf(id));
        entity.setOrderStatus(OrderStatus.valueOf(orderStatus));
        entity.setEmail("test@test.bg");
        entity.setConstructionSite(constructionSiteEntity);
        entity.setMaterialType(MaterialType.valueOf(materialsType));
        switch (materialsType) {
            case "FASTENERS":
                entity.setFasteners(materials);
                break;
            case "GALVANIZED_SHEET":
                entity.setGalvanisedSheets(materials);
                break;
            case "INSULATION":
                entity.setInsulation(materials);
                break;
            case "METAL":
                entity.setMetals(materials);
                break;
            case "PANELS":
                entity.setPanels(materials);
                break;
            case "REBAR":
                entity.setRebars(materials);
                break;
            case "SET":
                entity.setSets(materials);
                break;
            case "SERVICE":
                entity.setServices(materials);
                break;
            case "TRANSPORT":
                entity.setTransports(materials);
                break;
            case "UNSPECIFIED":
                entity.setUnspecified(materials);
                break;
        }
        entity.setConstructionSite(constructionSiteEntity);
        entity.setOrderNumber(12345);
        return entity;
    }
}
