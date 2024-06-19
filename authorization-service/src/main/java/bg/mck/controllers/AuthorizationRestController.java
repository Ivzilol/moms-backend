package bg.mck.controllers;

import bg.mck.model.AuthorizationDTO;
import bg.mck.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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


    @PostMapping("/isauthorized")
    @ExceptionHandler(NullPointerException.class)

    public ResponseEntity<?> isAuthorized(@RequestBody AuthorizationDTO authorizationDTO) {
        return authService.isAuthorized(authorizationDTO) ?
                ResponseEntity.ok(authService.isAuthorized(authorizationDTO)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
