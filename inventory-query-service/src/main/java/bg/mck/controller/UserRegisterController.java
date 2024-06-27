package bg.mck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    @PostMapping("/inventory/event")
    public ResponseEntity<Void> sendEvent(@RequestBody String data, @RequestHeader("Event-Type") String eventType) throws JsonProcessingException {
        System.out.println();
        return ResponseEntity.ok().build();
    }
}
