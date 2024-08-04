package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserStatusDTO;
import bg.mck.usercommandservice.application.service.UserProfileManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/user/command/profile")
public class UserProfileManagementController {

    private final UserProfileManagementService userProfileManagementService;

    public UserProfileManagementController(UserProfileManagementService userProfileManagementService) {
        this.userProfileManagementService = userProfileManagementService;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusDTO userStatusDTO) {
        userProfileManagementService.updateUserProfile(id, userStatusDTO);
        return ResponseEntity.ok().build();
    }

}
