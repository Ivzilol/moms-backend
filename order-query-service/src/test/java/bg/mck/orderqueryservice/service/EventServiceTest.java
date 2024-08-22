package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.client.NotificationServiceClient;
import bg.mck.orderqueryservice.dto.EmailDTO;
import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.OrderEntity;
import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import bg.mck.orderqueryservice.entity.enums.MaterialType;
import bg.mck.orderqueryservice.entity.enums.OrderStatus;
import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.mapper.MailMapper;
import bg.mck.orderqueryservice.mapper.OrderMapper;
import bg.mck.orderqueryservice.repository.EventRepository;
import bg.mck.orderqueryservice.repository.OrderRepository;
import bg.mck.orderqueryservice.utils.EventTypeUtils;
import bg.mck.orderqueryservice.utils.OrderMapperImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    private OrderMapperImpl utilMapper = new OrderMapperImpl();

    @Mock
    private RedisService redisService;

    @Mock
    private OrderMapper orderMapper;

    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private EventTypeUtils eventTypeUtils;

    @Mock
    private Gson gson;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private NotificationServiceClient notificationServiceClient;

    @Mock
    private MailMapper mailMapper;

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        MockitoAnnotations.openMocks(this);

        // Initialize EventTypeUtils and inject into EventService
        eventTypeUtils = new EventTypeUtils();
        ReflectionTestUtils.setField(eventService, "eventTypeUtils", eventTypeUtils);
        ReflectionTestUtils.setField(eventService, "executor", executorService);
    }

    // Test for FastenerEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateFastenerEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.FASTENERS, new FasterEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null).setClazz("slass")
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setDiameter("1 MM").setLength("2 MM").setQuantity("12").setStandard("standard").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<FasterEvent>>>(){}.getType());
    }

    // Test for FastenerEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateFastenerEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.FASTENERS, new FasterEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null).setClazz("slass")
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setDiameter("1 MM").setLength("2 MM").setQuantity("12").setStandard("standard").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<FasterEvent>>>(){}.getType());
    }

    // Test for RebarEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateRebarEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.REBAR, new RebarEvent().setId(1L).setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<RebarEvent>>>(){}.getType());
    }

    // Test for RebarEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateRebarEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.REBAR, new RebarEvent().setId(1L).setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<RebarEvent>>>(){}.getType());
    }

    // Test for GalvanisedSheetEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateGalvanisedSheetEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.GALVANIZED_SHEET, new GalvanisedSheetEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setNumberOfSheets("13").setMaxLength("12 M").setType("Type")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>>>(){}.getType());
    }

    // Test for GalvanisedSheetEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateGalvanisedSheetEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.GALVANIZED_SHEET, new GalvanisedSheetEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setNumberOfSheets("13").setMaxLength("12 M").setType("Type")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>>>(){}.getType());
    }

    // Test for InsulationEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateInsulationEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.INSULATION, new InsulationEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setThickness("12543").setType("Type")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<InsulationEvent>>>(){}.getType());
    }

    // Test for InsulationEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateInsulationEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.INSULATION, new InsulationEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setThickness("12543").setType("Type")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<InsulationEvent>>>(){}.getType());
    }

    // Test for MetalEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateMetalEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.METAL, new MetalEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setKind("Kind").setTotalWeight("123")
                .setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<MetalEvent>>>(){}.getType());
    }

    // Test for MetalEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateMetalEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.METAL, new MetalEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setKind("Kind").setTotalWeight("123")
                .setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<MetalEvent>>>(){}.getType());
    }

    // Test for PanelEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreatePanelEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.PANELS, new PanelEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setColor("Red").setFrontSheetThickness("123").setTotalThickness("13").setType("Type").setWidth("13")
                .setLength("2 MM").setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<PanelEvent>>>(){}.getType());
    }

    // Test for PanelEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdatePanelEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.PANELS, new PanelEvent().setId(1L)
                .setType("Fasteners").setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setColor("Red").setFrontSheetThickness("123").setTotalThickness("13").setType("Type").setWidth("13")
                .setLength("2 MM").setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<PanelEvent>>>(){}.getType());
    }

    // Test for SetEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateSetEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.SET, new SetEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test").setColor("Blue").setMaxLength("123 M")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<SetEvent>>>(){}.getType());
    }

    // Test for SetEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateSetEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.SET, new SetEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test").setColor("Blue").setMaxLength("123 M")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<SetEvent>>>(){}.getType());
    }

    // Test for UnspecifiedEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateUnspecifiedEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.UNSPECIFIED, new UnspecifiedEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<UnspecifiedEvent>>>(){}.getType());
    }

    // Test for UnspecifiedEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateUnspecifiedEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.UNSPECIFIED, new UnspecifiedEvent().setId(1L)
                .setAdminNote(null)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg"), new TypeToken<OrderEvent<CreateOrderEvent<UnspecifiedEvent>>>(){}.getType());
    }

    // Test for ServiceEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateServiceEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.SERVICE, new ServiceEvent().setId(1L)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("Test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg").setAdminNote(null), new TypeToken<OrderEvent<CreateOrderEvent<ServiceEvent>>>(){}.getType());
    }

    // Test for ServiceEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateServiceEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.SERVICE, new ServiceEvent().setId(1L)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("Test")
                .setQuantity("12").setSpecificationFileUrl("https://test.bg").setAdminNote(null), new TypeToken<OrderEvent<CreateOrderEvent<ServiceEvent>>>(){}.getType());
    }

    // Test for TransportEvent Create
    @Test
    void processOrderEvent_ShouldProcessCreateTransportEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeCreateTest(MaterialType.TRANSPORT, new TransportEvent().setId(1L)
                .setMaterialStatus(MaterialStatus.NOT_APPROVED).setDescription("test")
                .setQuantity("13").setAdminNote(null).setMaxLength("12 MM").setSpecificationFileUrl("https://test.bg")
                .setTruck("Truck").setWeight("12 KG"), new TypeToken<OrderEvent<CreateOrderEvent<TransportEvent>>>(){}.getType());
    }

    // Test for TransportEvent Update
    @Test
    void processOrderEvent_ShouldProcessUpdateTransportEvent() throws InvocationTargetException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, NoSuchFieldException {
        executeUpdateTest(MaterialType.TRANSPORT, new TransportEvent().setId(1L)
                .setMaterialStatus(MaterialStatus.APPROVED).setDescription("test")
                .setQuantity("13").setAdminNote(null).setMaxLength("12 MM").setSpecificationFileUrl("https://test.bg")
                .setTruck("Truck").setWeight("12 KG"), new TypeToken<OrderEvent<CreateOrderEvent<TransportEvent>>>(){}.getType());
    }

    // Helper method for executing create tests
    private <T extends BaseMaterialEvent> void executeCreateTest(MaterialType materialType, T materialEvent, Type type) throws JsonProcessingException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        String eventType = "ORDER_CREATED";
        setupTest(materialType, materialEvent, type, eventType);
    }

    // Helper method for executing update tests
    private <T extends BaseMaterialEvent> void executeUpdateTest(MaterialType materialType, T materialEvent, Type type) throws JsonProcessingException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        String eventType = "ORDER_UPDATED";
        setupTest(materialType, materialEvent, type, eventType);
    }

    // General setup method for create and update tests
    private <T extends BaseMaterialEvent> void setupTest(MaterialType materialType, T materialEvent, Type type, String eventType) throws JsonProcessingException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        // Setup order and material events
        CreateOrderEvent<T> createOrderEvent = new CreateOrderEvent<>();
        Field id = materialEvent.getClass().getDeclaredField("id");
        id.setAccessible(true);
        createOrderEvent.setOrderId((Long) id.get(materialEvent));
        createOrderEvent.setEmail("test@example.com");
        createOrderEvent.setOrderNumber(456);
        createOrderEvent.setOrderDescription("Order for construction site");
        createOrderEvent.setMaterialType(materialType);
        createOrderEvent.setOrderStatus(OrderStatus.CREATED);
        createOrderEvent.setMaterials(Collections.singleton(materialEvent));

        OrderEvent<CreateOrderEvent<T>> orderEvent = new OrderEvent<>();
        orderEvent.setEventType(OrderEventType.valueOf(eventType));
        orderEvent.setEvent(createOrderEvent);

        // Mock JSON serialization/deserialization
        String data = mapper.writeValueAsString(orderEvent);
        when(gson.fromJson(data, type)).thenReturn(orderEvent);
        OrderEntity orderEntity = utilMapper.toOrderEntity(createOrderEvent);
        when(orderRepository.findById(any(String.class))).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toOrderEntity(any(CreateOrderEvent.class))).thenReturn(orderEntity);

        // Invoke the method under test
        Method processOrderEvent = eventService.getClass().getDeclaredMethod("processOrderEvent", String.class, String.class);
        processOrderEvent.invoke(eventService, data, eventType);
        // Verify interactions
        verify(gson).fromJson(data, type);
        verify(orderService, times(1)).saveOrder(orderEntity);
        verify(eventRepository).save(orderEvent);
    }
}
