package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserLoginDTO;
import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.exceptions.InvalidEmailOrPasswordException;
import bg.mck.userqueryservice.application.exceptions.UserIsInActiveException;
import bg.mck.userqueryservice.application.mapper.UserMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static bg.mck.userqueryservice.application.constants.ApplicationConstants.APPLICATION_VERSION;


@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public UserLoginService(UserRepository userRepository, UserMapper userMapper, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
    }

    public UserLoginResponseDTO login(UserLoginDTO userDto) {
        if (!isUserExist(userDto.getEmail())) {
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        if (isPasswordCorrect(user, userDto.getPassword())) {
            if (!user.isActive()) {
                throw new UserIsInActiveException();
            }
            return userMapper.toUserResponseDTO(user);
        } else {
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }
    }

    private boolean isUserExist(String email) {
        List<UserEntity> all = userRepository.findAll();
        return userRepository.findByEmail(email) != null;
    }

    private boolean isPasswordCorrect(UserEntity user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }


    public String generateToken(UserLoginResponseDTO loggedUser) {

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://authentication-service/"+ APPLICATION_VERSION +"/authentication/generate-token",
                loggedUser,
                String.class
        );
        return responseEntity.getBody();
    }
}
