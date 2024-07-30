package bg.mck.ordercommandservice.client;

import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.event.EventData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-QUERY-SERVICE")
public interface OrderQueryServiceClient {

    @PostMapping("/orders/event")
    void sendEvent(@RequestBody EventData data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/construction-sites/event")
    void sendConstructionSiteEvent(@RequestBody EventData data, @RequestHeader("Event-Type") String eventType);
}
