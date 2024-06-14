package bg.mck.usercommandservice.application.client;

import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-QUERY-SERVICE", url = "http://localhost:8086")
public interface UserQueryServiceClient {

    @GetMapping("/users/{id}")
     UserCredentialsDTO getUserById(@PathVariable("id") Long id);
}
