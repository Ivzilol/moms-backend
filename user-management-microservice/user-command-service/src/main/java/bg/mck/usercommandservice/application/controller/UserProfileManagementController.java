package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserUpdateProfileDTO;
import bg.mck.usercommandservice.application.service.UserProfileManagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserProfileManagementController {

    private final UserProfileManagementService userProfileManagementService;

    public UserProfileManagementController(UserProfileManagementService userProfileManagementService) {
        this.userProfileManagementService = userProfileManagementService;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserUpdateProfileDTO userUpdateProfileDTO) {
        userProfileManagementService.updateUserProfile(id, userUpdateProfileDTO);
        return ResponseEntity.ok().build();
    }
}
