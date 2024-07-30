package bg.mck.orderqueryservice.utils;

import bg.mck.orderqueryservice.events.*;
import bg.mck.orderqueryservice.service.EventService;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventTypeUtils {

    private Map<String, Map<String, Type>> typeEvents;
    private Map<String, Method> orderMethodProcessors;
    private Map<String, Method> constructionMethodProcessors;
    private final Map<String, Type> typeOrderEvents = new HashMap<String, Type>() {
        {
            put("FASTENERS",
                    new TypeToken<OrderEvent<CreateOrderEvent<FasterEvent>>>() {
                    }.getType());
            put("GALVANIZED_SHEET",
                    new TypeToken<OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>>>() {
                    }.getType());
            put("INSULATION",
                    new TypeToken<OrderEvent<CreateOrderEvent<InsulationEvent>>>() {
                    }.getType());
            put("METAL",
                    new TypeToken<OrderEvent<CreateOrderEvent<MetalEvent>>>() {
                    }.getType());
            put("PANELS",
                    new TypeToken<OrderEvent<CreateOrderEvent<PanelEvent>>>() {
                    }.getType());
            put("REBAR",
                    new TypeToken<OrderEvent<CreateOrderEvent<RebarEvent>>>() {
                    }.getType());
            put("SET",

                    new TypeToken<OrderEvent<CreateOrderEvent<SetEvent>>>() {
                    }.getType());
            put("UNSPECIFIED",
                    new TypeToken<OrderEvent<CreateOrderEvent<UnspecifiedEvent>>>() {
                    }.getType());
            put("SERVICE",
                    new TypeToken<OrderEvent<CreateOrderEvent<ServiceEvent>>>() {
                    }.getType());
            put("TRANSPORT",
                    new TypeToken<OrderEvent<CreateOrderEvent<TransportEvent>>>() {
                    }.getType());
        }
    };

    public EventTypeUtils() throws NoSuchMethodException {
        initializeTypeEvents();
        initializeMethodProcessors();
    }

    private void initializeMethodProcessors() throws NoSuchMethodException {
        this.orderMethodProcessors = Map.of(
                OrderEventType.ORDER_CREATED.name(), EventService.class.getDeclaredMethod("processCreateEvent", String.class, String.class, String.class),
                OrderEventType.ORDER_UPDATED.name(), EventService.class.getDeclaredMethod("processUpdateEvent", String.class, String.class, String.class)
        );
        this.constructionMethodProcessors = Map.of(
                OrderEventType.CONSTRUCTION_SITE_CREATED.name(), EventService.class.getDeclaredMethod("processCreateConstructionSite", String.class),
                OrderEventType.CONSTRUCTION_SITE_UPDATED.name(), EventService.class.getDeclaredMethod("processUpdateConstructionSite", String.class)
        );
    }

    private void initializeTypeEvents() {
        this.typeEvents = new HashMap<>();
        this.typeEvents.put("ORDER_CREATED", this.typeOrderEvents);
        this.typeEvents.put("ORDER_UPDATED", this.typeOrderEvents);
    }

    public Map<String, Map<String, Type>> getTypeEvents() {
        return typeEvents;
    }


    public Map<String, Method> getOrderMethodProcessors() {
        return orderMethodProcessors;
    }

    public Map<String, Method> getConstructionMethodProcessors() {
        return constructionMethodProcessors;
    };
}
