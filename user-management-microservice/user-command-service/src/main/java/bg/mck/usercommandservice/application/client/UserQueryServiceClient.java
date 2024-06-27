package bg.mck.usercommandservice.application.client;

import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.events.BaseEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-QUERY-SERVICE")
public interface UserQueryServiceClient {

    @GetMapping("/users/credentials/{id}")
     UserCredentialsDTO getUserCredentialsById(@PathVariable("id") Long id);


    @PostMapping("/users/event")
     void sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType);

}
