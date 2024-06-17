package bg.mck.authenticationservice.controllers;

import bg.mck.authenticationservice.model.User;
import bg.mck.authenticationservice.services.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}")
public class AuthenticationRestController {

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationRestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/auth/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody User user) {

        String generatedToken = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getAuthorities());

        return ResponseEntity.status(HttpStatus.CREATED).body(generatedToken);
    }

    @GetMapping("/auth/validate")
    public ResponseEntity<String> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        try {
                if (!jwtUtil.isExpired(token)) {
                return ResponseEntity.status(HttpStatus.OK).body("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired");
            }
        } catch (MalformedJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired");
        }
    }
}
