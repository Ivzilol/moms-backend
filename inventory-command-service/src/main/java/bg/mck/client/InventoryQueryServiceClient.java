package bg.mck.client;

import bg.mck.dto.InventoryItemDetailsDTO;
import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:9004", name = "INVENTORY-QUERY-SERVICE")
public interface InventoryQueryServiceClient {


    @PostMapping("/inventory/events")
    <T extends BaseEvent> void sendEvent(@RequestBody MaterialEvent<T> data, @RequestHeader("Event-Type") String eventType);

    @GetMapping("/inventory/items/{id}")
    InventoryItemDetailsDTO getInventoryItemById(@PathVariable("id") Long id);

    @GetMapping("inventory/items/{id}/exists")
    boolean existsInventoryItemById(@PathVariable("id") Long id);

}