package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.dto.ErrorsRegistrationDTO;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.RegisteredUserEvent;
import bg.mck.usercommandservice.application.dto.UserRegisterDTO;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static bg.mck.usercommandservice.application.util.PasswordUtil.hashPassword;

@Service
public class UserRegisterService {


    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final KafkaPublisherService kafkaService;

    public UserRegisterService(AuthorityRepository authorityRepository, UserRepository userRepository, KafkaPublisherService kafkaService) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.kafkaService = kafkaService;
    }


    public void registerUser(UserRegisterDTO userRegisterDTO) {
        UserEntity user = new UserEntity();
        mapUser(user, userRegisterDTO);
        this.userRepository.save(user);
        UserEntity savedUser = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        RegisteredUserEvent event = new RegisteredUserEvent(
                EventType.UserRegistered,
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getPhoneNumber(),
                savedUser.isActive(),
                savedUser.getAuthorities().stream().map(r -> r.getAuthority().name()).collect(Collectors.toSet())
        );

        kafkaService.publishUserEvent(EventCreationHelper.toUserEvent(event));
    }

    private void mapUser(UserEntity user, UserRegisterDTO userRegisterDTO) {
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(hashPassword(userRegisterDTO.getPassword()));
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        Authority authority = new Authority();
        authority.setAuthority(AuthorityEnum.valueOf(userRegisterDTO.getRole()));
        this.authorityRepository.save(authority);
        if (user.getAuthorities() == null) {
            user.setAuthorities(new HashSet<>());
        }
        user.getAuthorities().add(authority);
    }


    public void setErrors(List<String> errors, ErrorsRegistrationDTO errorsRegistrationDTO) {
        for (String error : errors) {
            switch (error) {
                case "Email cannot be empty":
                    errorsRegistrationDTO.setEmailError("Email cannot be empty");
                case "Password cannot be empty":
                    errorsRegistrationDTO.setPasswordError("Password cannot be empty");
                case "Confirm password cannot be empty":
                    errorsRegistrationDTO.setConfirmPasswordError("Confirm password cannot be empty");
                case "First Name cannot be empty":
                    errorsRegistrationDTO.setFirstNameError("First Name cannot be empty");
                case "Last Name cannot be empty":
                    errorsRegistrationDTO.setLastNameError("Last Name cannot be empty");
                case "Phone Number cannot be empty":
                    errorsRegistrationDTO.setPhoneNumberError("Phone Number cannot be empty");
                case "Email should be valid":
                    errorsRegistrationDTO.setEmailError("Email should be valid");
                case "Email already exist":
                    errorsRegistrationDTO.setEmailError("Email already exist");
                case "The password must contain a minimum of 6 characters":
                    errorsRegistrationDTO.setPasswordError("The password must contain a minimum of 6 characters");
            }
        }
    }

    public String findUserByEmail(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        UserEntity user = userRepository.findByEmail(value);
        if (user == null) {
            return null;
        } else {
            return user.getEmail();
        }
    }
}
