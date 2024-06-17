package bg.mck.usercommandservice.application.client;

import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-QUERY-SERVICE", url = "http://localhost:8086")
public interface UserQueryServiceClient {

    @GetMapping("/auth/credentials/{id}")
     UserCredentialsDTO getUserCredentialsById(@PathVariable("id") Long id);

}
