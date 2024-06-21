package bg.mck.usercommandservice.application.client;

import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.events.BaseEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//url = "http://localhost:8086"
@FeignClient(name = "USER-QUERY-SERVICE")
public interface UserQueryServiceClient {

    @GetMapping("/auth/credentials/{id}")
     UserCredentialsDTO getUserCredentialsById(@PathVariable("id") Long id);


    @PostMapping("/users/event")
    <T extends BaseEvent> void sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType);

}
