package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.service.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserQueryController {

    private final UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/auth/credentials/{id}")
    public ResponseEntity<UserCredentialsDTO> getUserCredentialsById(@PathVariable Long id) {

        UserCredentialsDTO userDto = userQueryService.getUserCredentialsById(id);
        return ResponseEntity.ok(userDto);
    }
}