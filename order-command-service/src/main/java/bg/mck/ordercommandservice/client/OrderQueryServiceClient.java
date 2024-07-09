package bg.mck.ordercommandservice.client;

import bg.mck.ordercommandservice.event.OrderEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-QUERY-SERVICE")
public interface OrderQueryServiceClient {

    @PostMapping("/orders/event")
    void sendEvent(@RequestBody OrderEvent data, @RequestHeader("Event-Type") String eventType);

}
