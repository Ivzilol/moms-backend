package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.OrderQueryServiceApplication;
import bg.mck.orderqueryservice.client.NotificationServiceClient;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.entity.FastenerEntity;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.repository.ConstructionSiteRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrderQueryServiceApplication.class)
@AutoConfigureMockMvc
public class EventServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private NotificationServiceClient notificationServiceClient;

    private OrderEntity order;

    private ConstructionSiteEntity constructionSiteEntity;

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
        RedisServer redisServer = new RedisServerBuilder()
                .setting("save 3600 1") // Adjust saving frequency
                .setting("stop-writes-on-bgsave-error no") // Prevent stopping writes on save errors
                .setting("appendonly no") // Disable AOF for tests
                .setting("maxmemory 128M") // Limit memory usage
                .build();
        redisServer.start();
        when(notificationServiceClient.sendNotification(any())).thenReturn(null);
    }

    @Test
    void testOrderCreatedWithFasterEvent() throws Exception {

        CreateOrderEvent<FasterEvent> createOrderEvent = new CreateOrderEvent<>();
        createOrderEvent.setOrderId(1L);
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterialType(MaterialType.FASTENERS);
        createOrderEvent.setEventType(OrderEventType.ORDER_CREATED);
        createOrderEvent.setEmail("test@test.bg");
        createOrderEvent.setMaterials(Set.of(new FasterEvent().setId(1L).setType("Fastener")));
        createOrderEvent.setOrderNumber(12345);
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId("1");
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
        OrderEntity entity = orderRepository.findById("1").get();

        boolean equals = Objects.equals(entity, order);
        System.out.println(entity);
        System.out.println(order);
        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getEmail(), order.getEmail());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());

    }

    private OrderEntity getEntityToCheck(String orderStatus, String materialsType, Set materials) {
        OrderEntity entity = new OrderEntity();
        entity.setId("1");
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
