package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.exceptions.InvalidEmailOrPasswordException;
import bg.mck.userqueryservice.application.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }


    @Operation(summary = "User login")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successfully log in."),
                    @ApiResponse(responseCode = "400", description = "Incorrect credentials.",
                            content = {@Content(mediaType = "application/json")})
            }
    )
    @PostMapping("/${APPLICATION_VERSION}/user/user/query/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO,
                                                      HttpServletResponse response) {

        UserLoginResponseDTO loggedUser = userLoginService.login(userLoginDTO);

        String token = userLoginService.generateToken(loggedUser);
        loggedUser.setToken("Bearer " + token);
        response.setHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok(loggedUser);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<String> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
