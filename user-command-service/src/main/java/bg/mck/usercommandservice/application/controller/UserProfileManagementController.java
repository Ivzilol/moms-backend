package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserDetailsDTO;
import bg.mck.usercommandservice.application.service.UserProfileManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/command/profile")
public class UserProfileManagementController {

    private final UserProfileManagementService userProfileManagementService;

    public UserProfileManagementController(UserProfileManagementService userProfileManagementService) {
        this.userProfileManagementService = userProfileManagementService;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserDetailsDTO userDetailsDTO) {
        userProfileManagementService.updateUserProfile(id, userDetailsDTO);
        return ResponseEntity.ok().build();
    }
}
