package bg.mck.utils;

import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.events.transport.BaseTransportEvent;
import bg.mck.events.transport.TransportEvent;

public class EventCreationHelper {

    public static <T extends BaseMaterialEvent> MaterialEvent<T> toMaterialEvent(T event) {
        MaterialEvent<T> materialEvent = new MaterialEvent<>();
        materialEvent.setEventType(event.getEventType());
        materialEvent.setEvent(event);
        return materialEvent;
    }

    public static <T extends BaseServiceEvent> ServiceEvent<T> toServiceEvent(T event) {
        ServiceEvent<T> serviceEvent = new ServiceEvent<>();
        serviceEvent.setEventType(event.getEventType());
        serviceEvent.setEvent(event);
        return serviceEvent;
    }

    public static <T extends BaseTransportEvent> TransportEvent<T> toTransportEvent(T event) {
        TransportEvent<T> transportEvent = new TransportEvent<>();
        transportEvent.setEventType(event.getEventType());
        transportEvent.setEvent(event);
        return transportEvent;
    }

    public static <T extends BaseConstructionEvent> ConstructionEvent<T> toConstructionEvent(T event) {
        ConstructionEvent<T> constructionEvent = new ConstructionEvent<>();
        constructionEvent.setEventType(event.getEventType());
        constructionEvent.setEvent(event);
        return constructionEvent;
    }



}
