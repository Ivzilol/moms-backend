package bg.mck.userqueryservice.application.controller;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.service.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginRestController {

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginRestController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }


    @PostMapping("/${APPLICATION_VERSION}/users/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO,
                                                      HttpServletResponse response) {

        UserLoginResponseDTO loggedUser = userLoginService.login(userLoginDTO);

        String token = userLoginService.generateToken(loggedUser);
        response.setHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok(loggedUser);
    }
}
