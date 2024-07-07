package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/superadmin/user/query")
public class UsersController {

    private final UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Retrieve all users details", description = "Giving information about all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "No users in the database",
                    content = @Content(mediaType = "application/json"))
    })
    @RequestMapping("/getallusers")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll().stream().map(u ->
                        new UserDetailsDTO()
                                .setActive(u.isActive())
                                .setEmail(u.getEmail())
                                .setId(u.getId())
                                .setFirstName(u.getFirstName())
                                .setLastName(u.getLastName())
                                .setPhoneNumber(u.getPhoneNumber())
                                .setRoles(u.getRoles()))
                .toList());
    }
}
