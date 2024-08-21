package bg.mck.orderqueryservice.utils;

import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.service.EventService;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventTypeUtils {

    private final Map<String, Map<String, Type>> typeEvents;
    private final Map<String, Method> orderMethodProcessors;
    private final Map<String, Method> constructionMethodProcessors;

    private static final Map<String, Type> TYPE_ORDER_EVENTS = createTypeOrderEvents();

    public EventTypeUtils() {
        this.typeEvents = initializeTypeEvents();
        this.orderMethodProcessors = initializeOrderMethodProcessors();
        this.constructionMethodProcessors = initializeConstructionMethodProcessors();
    }

    private static Map<String, Type> createTypeOrderEvents() {
        Map<String, Type> typeOrderEvents = new HashMap<>();
        typeOrderEvents.put("FASTENERS", new TypeToken<OrderEvent<CreateOrderEvent<FasterEvent>>>() {}.getType());
        typeOrderEvents.put("GALVANIZED_SHEET", new TypeToken<OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>>>() {}.getType());
        typeOrderEvents.put("INSULATION", new TypeToken<OrderEvent<CreateOrderEvent<InsulationEvent>>>() {}.getType());
        typeOrderEvents.put("METAL", new TypeToken<OrderEvent<CreateOrderEvent<MetalEvent>>>() {}.getType());
        typeOrderEvents.put("PANELS", new TypeToken<OrderEvent<CreateOrderEvent<PanelEvent>>>() {}.getType());
        typeOrderEvents.put("REBAR", new TypeToken<OrderEvent<CreateOrderEvent<RebarEvent>>>() {}.getType());
        typeOrderEvents.put("SET", new TypeToken<OrderEvent<CreateOrderEvent<SetEvent>>>() {}.getType());
        typeOrderEvents.put("UNSPECIFIED", new TypeToken<OrderEvent<CreateOrderEvent<UnspecifiedEvent>>>() {}.getType());
        typeOrderEvents.put("SERVICE", new TypeToken<OrderEvent<CreateOrderEvent<ServiceEvent>>>() {}.getType());
        typeOrderEvents.put("TRANSPORT", new TypeToken<OrderEvent<CreateOrderEvent<TransportEvent>>>() {}.getType());
        return Collections.unmodifiableMap(typeOrderEvents);
    }

    private Map<String, Map<String, Type>> initializeTypeEvents() {
        return Map.of(OrderEventType.ORDER_CREATED.name(), TYPE_ORDER_EVENTS, OrderEventType.ORDER_UPDATED.name(), TYPE_ORDER_EVENTS);
    }

    private Map<String, Method> initializeOrderMethodProcessors() {
        try {
            return Map.of(
                    OrderEventType.ORDER_CREATED.name(), EventService.class.getDeclaredMethod("processCreateEvent", String.class, String.class, String.class),
                    OrderEventType.ORDER_UPDATED.name(), EventService.class.getDeclaredMethod("processUpdateEvent", String.class, String.class, String.class)
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Error initializing order method processors", e);
        }
    }

    private Map<String, Method> initializeConstructionMethodProcessors() {
        try {
            return Map.of(
                    OrderEventType.CONSTRUCTION_SITE_CREATED.name(), EventService.class.getDeclaredMethod("processCreateConstructionSite", String.class),
                    OrderEventType.CONSTRUCTION_SITE_UPDATED.name(), EventService.class.getDeclaredMethod("processUpdateConstructionSite", String.class)
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Error initializing construction method processors", e);
        }
    }

    public Map<String, Map<String, Type>> getTypeEvents() {
        return typeEvents;
    }

    public Map<String, Method> getOrderMethodProcessors() {
        return orderMethodProcessors;
    }

    public Map<String, Method> getConstructionMethodProcessors() {
        return constructionMethodProcessors;
    }
}
