package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserUpdatedDTO;
import bg.mck.usercommandservice.application.dto.UserStatusUpdatedDTO;
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
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusUpdatedDTO userStatusUpdatedDTO) {
        userProfileManagementService.updateUserStatus(id, userStatusUpdatedDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserUpdatedDTO userUpdatedDTO) {
        userProfileManagementService.updateUserProfile(id, userUpdatedDTO);
        return ResponseEntity.ok().build();
    }

}
