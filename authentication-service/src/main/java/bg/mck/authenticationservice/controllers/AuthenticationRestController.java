package bg.mck.authenticationservice.controllers;

import bg.mck.authenticationservice.model.User;
import bg.mck.authenticationservice.services.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/authentication")
@Tag(name = "Authentication API", description = "Endpoints for authentication and JWT token management")
public class AuthenticationRestController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationRestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Generate JWT token", description = "Generates a JWT token for a given user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Token successfully generated",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid user data")
    })
    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(
            @Parameter(description = "User credentials to generate the JWT token", required = true)
            @RequestBody User user) {

        String generatedToken = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRoles());
        logger.info("User {} has been successfully authenticated", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedToken);
    }

    @Operation(summary = "Validate JWT token", description = "Validates a given JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Token is expired or malformed")
    })
    @GetMapping("/validate")
    public ResponseEntity<String> validate(
            @Parameter(description = "JWT token to validate", required = true)
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        token = token.startsWith("Bearer ") ? token.substring(7) : token;
        try {
            if (!jwtUtil.isExpired(token)) {
                logger.info("Token is valid");
                return ResponseEntity.status(HttpStatus.OK).body("Token is valid");
            } else {
                logger.info("Token is expired or malformed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or malformed");
            }
        } catch (MalformedJwtException e) {
            logger.error("Token validation failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or malformed");
        }
    }

    @Operation(summary = "Get roles from JWT token", description = "Retrieves roles associated with a given JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or malformed token")
    })
    @GetMapping("/getroles/{token}")
    public ResponseEntity<String> getRoles(
            @Parameter(description = "JWT token to extract roles", required = true)
            @PathVariable String token) {
        return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.getRoles(token));
    }

    @Operation(summary = "Get email from JWT token", description = "Retrieves the email associated with a given JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or malformed token")
    })
    @GetMapping("/getemail/{token}")
    public ResponseEntity<String> getEmail(
            @Parameter(description = "JWT token to extract email", required = true)
            @PathVariable String token) {
        return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.getEmail(token));
    }
}