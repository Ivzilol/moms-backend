package bg.mck.userqueryservice.application.controller;


import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.service.UserProfileManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileManagementController {

    private final UserProfileManagementService userProfileManagementService;

    public UserProfileManagementController(UserProfileManagementService userProfileManagementService) {
        this.userProfileManagementService = userProfileManagementService;
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long id) {
        UserDetailsDTO  user = userProfileManagementService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth/credentials/{id}")
    public ResponseEntity<UserCredentialsDTO> getUserCredentialsById(@PathVariable Long id) {
        UserCredentialsDTO user = userProfileManagementService.getUserCredentialsById(id);
        return ResponseEntity.ok(user);
    }
}
