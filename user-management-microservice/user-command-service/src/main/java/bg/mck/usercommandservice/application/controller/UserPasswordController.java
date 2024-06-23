package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.UserChangePasswordDTO;
import bg.mck.usercommandservice.application.service.UserChangePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/users")
public class UserPasswordController {

    private final UserChangePasswordService userChangePasswordService;

    public UserPasswordController(UserChangePasswordService userChangePasswordService) {
        this.userChangePasswordService = userChangePasswordService;
    }

    @Operation(summary = "Update password")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Password successfully changed."),
                    @ApiResponse(responseCode = "400", description = "Incorrect field.",
                            content = {@Content(mediaType = "application/json")})
            }
    )
    @PatchMapping("/change-password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        userChangePasswordService.changePassword(id, userChangePasswordDTO);
        return ResponseEntity.ok().build();
    }
}
