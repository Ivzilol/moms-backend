package bg.mck.notificationservice.controller;

import bg.mck.notificationservice.dto.OrderDTO;
import bg.mck.notificationservice.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final MailService mailService;

    public NotificationController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/sendNotification", consumes = "application/json")
    public ResponseEntity<?> sendNotification(@RequestBody String order) throws JsonProcessingException, MessagingException {
        mailService.sendMessage(order);
        return ResponseEntity.ok().build();
    }
}
