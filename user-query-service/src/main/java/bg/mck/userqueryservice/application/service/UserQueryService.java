package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.exceptions.UserNotFoundException;
import bg.mck.userqueryservice.application.mapper.UserMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RedisService redisService;
    private final EventService eventService;

    public UserQueryService(UserRepository userRepository, UserMapper mapper, RedisService redisService, EventService eventService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.redisService = redisService;
        this.eventService = eventService;
    }

    public UserDetailsDTO getUserDetailsById(Long id) {
        UserEntity user = getUserById(id);
        return mapper.toDTO(user);
    }

    public UserCredentialsDTO getUserCredentialsById(Long id) {
        UserEntity user = getUserById(id);
        return mapper.toUserCredentialsDTO(user);
    }

    private UserEntity getUserById(Long id) {

        UserEntity cachedUser = redisService.getCachedObject(id);
        if (cachedUser != null) {
            return cachedUser;
        }
        userRepository.findById(id.toString()).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found"));


        return eventService.reconstructUserEntity(id);
    }

    public List<UserDetailsDTO> getAllUsersAsDTO() {
        List<UserDetailsDTO> usersDtos = userRepository.findAll().stream().map(u ->
                        new UserDetailsDTO()
                                .setActive(u.isActive())
                                .setEmail(u.getEmail())
                                .setId(u.getId())
                                .setFirstName(u.getFirstName())
                                .setLastName(u.getLastName())
                                .setPhoneNumber(u.getPhoneNumber())
                                .setRoles(u.getRoles()))
                .toList();
        if (usersDtos.isEmpty()) {
            throw new UserNotFoundException("No users in the base.");
        }
        return usersDtos;
    }

    public String getFullNameByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
        return user.getFirstName() + " " + user.getLastName();
    }
}

