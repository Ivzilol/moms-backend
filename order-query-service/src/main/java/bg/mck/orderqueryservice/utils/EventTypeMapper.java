package bg.mck.orderqueryservice.utils;

import bg.mck.orderqueryservice.events.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventTypeMapper {

    private Map<String, Map<String, Type>> typeEvents;

    public EventTypeMapper() {
        this.typeEvents = new HashMap<>();
        this.typeEvents.put("ORDER_CREATED",
                new HashMap<>() {
                    {
                        put("FASTENERS",
                                new TypeToken<OrderEvent<CreateOrderEvent<FasterEvent>>>() {}.getType());
                        put("GALVANIZED_SHEET",
                                new TypeToken<OrderEvent<CreateOrderEvent<GalvanisedSheetEvent>>>() {}.getType());
                        put("INSULATION",
                                new TypeToken<OrderEvent<CreateOrderEvent<InsulationEvent>>>() {}.getType());
                        put("METAL",
                                new TypeToken<OrderEvent<CreateOrderEvent<MetalEvent>>>() {}.getType());
                        put("PANELS",
                                new TypeToken<OrderEvent<CreateOrderEvent<PanelEvent>>>() {}.getType());
                        put("REBAR",
                                new TypeToken<OrderEvent<CreateOrderEvent<RebarEvent>>>() {}.getType());
                        put("SET",

                                new TypeToken<OrderEvent<CreateOrderEvent<SetEvent>>>() {}.getType());
                        put("UNSPECIFIED",
                                new TypeToken<OrderEvent<CreateOrderEvent<UnspecifiedEvent>>>() {}.getType());
                        put("SERVICE",
                                new TypeToken<OrderEvent<CreateOrderEvent<ServiceEvent>>>() {}.getType());
                        put("TRANSPORT",
                                new TypeToken<OrderEvent<CreateOrderEvent<TransportEvent>>>() {}.getType());
                    }
                }
                );
    }

    public Map<String, Map<String, Type>> getTypeEvents() {
        return typeEvents;
    }
}
