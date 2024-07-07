package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserQueryController {

    private final UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Retrieve User Credentials by ID", description = "Fetches the credentials of a user given their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credentials successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialsDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/${APPLICATION_VERSION}/user/user/query/credentials/{id}")
    public ResponseEntity<UserCredentialsDTO> getUserCredentialsById(@PathVariable Long id) {
        UserCredentialsDTO userDto = userQueryService.getUserCredentialsById(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Retrieve User Details by ID", description = "Fetches detailed information of a user given their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/${APPLICATION_VERSION}/user/user/query/user/{id}")
    public ResponseEntity<UserDetailsDTO> getUserDetailsById(@PathVariable Long id) {
        UserDetailsDTO userDto = userQueryService.getUserDetailsById(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }
}