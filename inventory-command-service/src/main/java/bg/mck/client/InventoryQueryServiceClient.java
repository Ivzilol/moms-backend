package bg.mck.client;

import bg.mck.events.BaseEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "http://localhost:9004")
public interface InventoryQueryServiceClient {


    @PostMapping("/inventory/event")
    static <T extends BaseEvent> void sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) {
        System.out.println();
    }

}
