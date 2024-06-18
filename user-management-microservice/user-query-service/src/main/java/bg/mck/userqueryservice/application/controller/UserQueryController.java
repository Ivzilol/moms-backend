package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.service.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserQueryController {

    private final UserQueryService queryService;

    public UserQueryController(UserQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/auth/credentials/{id}")
    public ResponseEntity<UserCredentialsDTO> getUserCredentialsById(@PathVariable Long id) {
        UserCredentialsDTO user = queryService.getUserCredentialsById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetailsDTO> getUserProfileById(@PathVariable Long id) {
        UserDetailsDTO  user = queryService.getUserDetailsById((id));
        return ResponseEntity.ok(user);
    }
}
