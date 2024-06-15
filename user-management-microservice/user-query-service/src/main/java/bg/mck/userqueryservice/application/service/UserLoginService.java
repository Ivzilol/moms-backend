package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.exceptions.InvalidEmailOrPasswordException;
import bg.mck.userqueryservice.application.mapper.UserQueryMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserLoginService {


    private final UserRepository userRepository;
    private final UserQueryMapper userQueryMapper;

    @Autowired
    public UserLoginService(UserRepository userRepository, UserQueryMapper userQueryMapper) {
        this.userRepository = userRepository;
        this.userQueryMapper = userQueryMapper;
    }

    public UserLoginResponseDTO login(UserLoginDTO userDto) {
        if (!isUserExist(userDto.getEmail())) {
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        if (isPasswordCorrect(user, userDto.getPassword())) {
            return userQueryMapper.toUserResponseDTO(user);
        } else {
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }
    }

    private boolean isUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean isPasswordCorrect(UserEntity user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }


    public String generateToken(UserLoginResponseDTO loggedUser) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://127.0.0.1:8081/auth/generate-token",
                loggedUser,
                String.class
        );
        return responseEntity.getBody();
    }
}
