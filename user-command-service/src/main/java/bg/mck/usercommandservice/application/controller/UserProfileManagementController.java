package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserDetailsDTO;
import bg.mck.usercommandservice.application.dto.UserStatusDTO;
import bg.mck.usercommandservice.application.service.UserProfileManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/superadmin/user/command/profile")
public class UserProfileManagementController {

    private final UserProfileManagementService userProfileManagementService;

    public UserProfileManagementController(UserProfileManagementService userProfileManagementService) {
        this.userProfileManagementService = userProfileManagementService;
    }


    @PatchMapping("/status/{id}")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusDTO userStatusDTO) {
        userProfileManagementService.updateUserStatus(id, userStatusDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserDetailsDTO userDetailsDTO) {
        userProfileManagementService.updateUserProfile(id, userDetailsDTO);
        return ResponseEntity.ok().build();
    }

}
