package bg.mck.usercommandservice.application.client;

import bg.mck.usercommandservice.application.dto.ForgotPasswordDTO;
import bg.mck.usercommandservice.application.dto.ForgotPasswordEmailDTO;
import bg.mck.usercommandservice.application.entity.ForgotPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationServiceClient {

    @PostMapping(value = "/sendResetEmail", consumes = "application/json")
    ResponseEntity<?> sendResetPassword(@RequestBody ForgotPasswordEmailDTO dto);
}
