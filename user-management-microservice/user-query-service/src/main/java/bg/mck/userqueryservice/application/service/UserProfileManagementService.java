package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.events.ProfileUpdatedEvent;
import bg.mck.userqueryservice.application.exceptions.UserNotFoundException;
import bg.mck.userqueryservice.application.mapper.UserMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserProfileManagementService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserProfileManagementService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public UserDetailsDTO getUserById(Long id) {
       UserEntity user =  findUserById(id);
       return mapper.toDTO(user);
    }

    public UserCredentialsDTO getUserCredentialsById(Long id) {
        UserEntity user =  findUserById(id);
        return mapper.toUserCredentialsDTO(user);
    }

    public void updateUserProfile(ProfileUpdatedEvent event) {
        UserEntity userEntity = findUserById(event.getUserId());

        userEntity.setFirstName(event.getFirstName());
        userEntity.setLastName(event.getLastName());
        userEntity.setPhoneNumber(event.getPhoneNumber());

        userRepository.save(userEntity);
    }

    private UserEntity findUserById(Long id) {
        return userRepository.findById(String.valueOf(id)).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));
    }
}
