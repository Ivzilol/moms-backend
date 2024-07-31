package bg.mck.orderqueryservice.client;

import bg.mck.orderqueryservice.dto.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationServiceClient {

    @PostMapping(value = "/sendNotification", consumes = "application/json")
    ResponseEntity<?> sendNotification(@RequestBody EmailDTO order);
}
