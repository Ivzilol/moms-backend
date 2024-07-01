package bg.mck.client;

import bg.mck.events.BaseEvent;
import bg.mck.events.MaterialEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "http://localhost:9004", name = "inventory-query-service")
public interface InventoryQueryServiceClient {


    @PostMapping("/inventory/event")
    <T extends BaseEvent> void sendEvent(@RequestBody MaterialEvent data, @RequestHeader("Event-Type") String eventType);

}