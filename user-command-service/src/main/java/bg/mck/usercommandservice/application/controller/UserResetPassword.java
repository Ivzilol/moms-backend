package bg.mck.usercommandservice.application.controller;

import bg.mck.usercommandservice.application.dto.ErrorsRegistrationDTO;
import bg.mck.usercommandservice.application.dto.ForgotPasswordDTO;
import bg.mck.usercommandservice.application.dto.ResetPasswordDTO;
import bg.mck.usercommandservice.application.exceptions.EmailNotFoundException;
import bg.mck.usercommandservice.application.service.ForgotPasswordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/user/command/reset-password")
public class UserResetPassword {

    private ForgotPasswordService forgotPasswordService;

    public UserResetPassword(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @Operation(summary = "Request reset password")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successfully requested password reset"),
                    @ApiResponse(responseCode = "400", description = "The token is already received on the email"),
                    @ApiResponse(responseCode = "404", description = "Email not found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmailNotFoundException.class))})
            }
    )
    @PostMapping
    public ResponseEntity<?> requestResetPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        forgotPasswordService.createResetPassword(forgotPasswordDTO.getEmail());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reset password")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successfully reset the password"),
                    @ApiResponse(responseCode = "400", description = "Provided token is invalid",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmailNotFoundException.class))})
            }
    )
    @PatchMapping("/update-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) throws JsonProcessingException {
        forgotPasswordService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok().build();
    }
}
