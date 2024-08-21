package bg.mck.controllers;

import bg.mck.model.AuthorizationDTO;
import bg.mck.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/authorization")
public class AuthorizationRestController {

    private final AuthService authService;

    @Autowired
    public AuthorizationRestController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Check if the user is authorized",
            description = "Validates the user's authorization based on provided credentials."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is authorized"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User is not authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Null pointer exception occurred")
    })
    @PostMapping("/isauthorized")
    public ResponseEntity<Void> isAuthorized(
            @Parameter(description = "Authorization credentials to validate", required = true)
            @RequestBody AuthorizationDTO authorizationDTO) {

        boolean isAuthorized = authService.isAuthorized(authorizationDTO);
        return isAuthorized ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("A null pointer exception occurred.");
    }
}