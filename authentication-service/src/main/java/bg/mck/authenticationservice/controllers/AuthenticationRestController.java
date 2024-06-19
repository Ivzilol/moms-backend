package bg.mck.authenticationservice.controllers;

import bg.mck.authenticationservice.model.User;
import bg.mck.authenticationservice.services.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/${APPLICATION_VERSION}/authentication")
public class AuthenticationRestController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationRestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody User user) {

        String generatedToken = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getAuthorities());
        logger.error("User {} has been successfully authenticated", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedToken);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        try {
            if (!jwtUtil.isExpired(token)) {
                logger.info("Token is valid");
                return ResponseEntity.status(HttpStatus.OK).body("Token is valid");
            } else {
                logger.info("Token is expired or malformed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or malformed");
            }
        } catch (MalformedJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired");
        }
    }

    @GetMapping("/getroles/{token}")
    public ResponseEntity<String> getRoles(@PathVariable String token) {
        return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.getRoles(token));
    }
}
