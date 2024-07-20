package bg.mck.client;

import bg.mck.dto.InventoryItemDetailsDTO;
import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.events.transport.BaseTransportEvent;
import bg.mck.events.transport.TransportEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-QUERY-SERVICE")
public interface InventoryQueryServiceClient {


    @PostMapping("/inventory/materials/events")
    <T extends BaseMaterialEvent> void sendMaterialEvent(@RequestBody MaterialEvent<T> data, @RequestHeader("Event-Type") String eventType,
                                                         @RequestHeader("Material-Type") String materialType);

    @PostMapping("/inventory/services/events")
    <T extends BaseServiceEvent> void sendServiceEvent(@RequestBody ServiceEvent<T> data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/inventory/transports/events")
    <T extends BaseTransportEvent> void sendTransportEvent(@RequestBody TransportEvent<T> data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/inventory/constructions/events")
    <T extends BaseConstructionEvent> void sendConstructionEvent(@RequestBody ConstructionEvent<T> data, @RequestHeader("Event-Type") String eventType);

    @GetMapping("/inventory/items/{id}")
    InventoryItemDetailsDTO getInventoryItemById(@PathVariable("id") Long id);

    @GetMapping("inventory/items/{id}/exists")
    boolean existsInventoryItemById(@PathVariable("id") Long id);

}