package bg.mck.ordercommandservice.client;

import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.event.eventData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-QUERY-SERVICE")
public interface OrderQueryServiceClient {

    @PostMapping("/orders/event")
    void sendEvent(@RequestBody eventData data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/orders/event/update")
    void sendUpdateEvent(@RequestBody UpdateOrderDTO updateOrderDTO, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/construction-sites/event")
    void sendConstructionSiteEvent(@RequestBody eventData data, @RequestHeader("Event-Type") String eventType);
}
