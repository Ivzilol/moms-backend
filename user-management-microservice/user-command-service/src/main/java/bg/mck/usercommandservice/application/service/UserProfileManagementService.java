package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.dto.UserUpdateProfileDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.EvenType;
import bg.mck.usercommandservice.application.events.UserProfileUpdatedEvent;
import bg.mck.usercommandservice.application.exceptions.InvalidPasswordException;
import bg.mck.usercommandservice.application.exceptions.UserProfileNotFoundException;
import bg.mck.usercommandservice.application.mapper.UserUpdateProfileMapper;
import bg.mck.usercommandservice.application.repository.UserRepository;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserProfileManagementService {

    private final UserRepository userRepository;
    private final UserUpdateProfileMapper userUpdateProfileMapper;
    private final UserQueryServiceClient userQueryClient;
    private final KafkaPublisherService kafkaService;



    public UserProfileManagementService(UserRepository userRepository, UserUpdateProfileMapper userUpdateProfileMapper, UserQueryServiceClient userQueryClient, KafkaPublisherService kafkaService) {
        this.userRepository = userRepository;
        this.userUpdateProfileMapper = userUpdateProfileMapper;
        this.userQueryClient = userQueryClient;
        this.kafkaService = kafkaService;
    }

    public void updateUserProfile(Long id, UserUpdateProfileDTO dto) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException("User with ID: " + id + " not found."));

        UserCredentialsDTO actualUserCredentials = userQueryClient.getUserById(id);
        boolean isPasswordCorrect = BCrypt.checkpw(dto.getPassword(), actualUserCredentials.getHashedPassword());

        if (!isPasswordCorrect) {
            throw new InvalidPasswordException("Invalid password for user with email: " + actualUserCredentials.getEmail());
        }

        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(userEntity);

        UserProfileUpdatedEvent event = new UserProfileUpdatedEvent(EvenType.UserProfileUpdated)
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setPhoneNumber(dto.getPhoneNumber());

        kafkaService.publish(event);

    }


}
