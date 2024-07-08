package bg.mck.usercommandservice.application.controller;


import bg.mck.usercommandservice.application.dto.ErrorsRegistrationDTO;
import bg.mck.usercommandservice.application.dto.UserRegisterDTO;
import bg.mck.usercommandservice.application.service.UserRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static bg.mck.usercommandservice.application.exceptions.ErrorUserRegistrationExceptions.PASSWORDS_NOT_MATCH;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/superadmin/user/command/register")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Operation(summary = "Register")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successful registration"),
                    @ApiResponse(responseCode = "400", description = "Incorrect field",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorsRegistrationDTO.class))})
            }
    )
    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO,
                                          BindingResult result) {
        ResponseEntity<ErrorsRegistrationDTO> errorsRegistrationDTO =
                errorRegistration(userRegisterDTO, result);
        if (errorsRegistrationDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsRegistrationDTO.getBody());
        }
        this.userRegisterService.registerUser(userRegisterDTO);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<ErrorsRegistrationDTO> errorRegistration(UserRegisterDTO userRegistrationDTO, BindingResult result) {
        ErrorsRegistrationDTO errorsRegistrationDTO = new ErrorsRegistrationDTO();
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            this.userRegisterService.setErrors(errors, errorsRegistrationDTO);
            return ResponseEntity.ok().body(errorsRegistrationDTO);
        }
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            errorsRegistrationDTO.setConfirmPasswordError(PASSWORDS_NOT_MATCH);
            return ResponseEntity.ok().body(errorsRegistrationDTO);
        }
        return null;
    }
}
