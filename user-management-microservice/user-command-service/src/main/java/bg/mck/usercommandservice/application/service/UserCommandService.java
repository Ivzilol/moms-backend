package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.dto.ErrorsRegistrationDTO;
import bg.mck.usercommandservice.application.dto.UserEvent;
import bg.mck.usercommandservice.application.dto.UserRegisterDTO;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserCommandEntity;
import bg.mck.usercommandservice.application.repository.AuthorityCommandRepository;
import bg.mck.usercommandservice.application.repository.UserCommandRepository;
import bg.mck.usercommandservice.application.util.PasswordUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static bg.mck.usercommandservice.application.util.PasswordUtil.hashPassword;

@Service
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;

    private final AuthorityCommandRepository authorityCommandRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public UserCommandService(UserCommandRepository userCommandRepository, AuthorityCommandRepository authorityCommandRepository, PasswordUtil passwordUtil, KafkaTemplate<String, Object> kafkaTemplate) {
        this.userCommandRepository = userCommandRepository;
        this.authorityCommandRepository = authorityCommandRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        UserCommandEntity user = new UserCommandEntity();
        mapUser(user, userRegisterDTO);
        this.userCommandRepository.save(user);
        UserCommandEntity savedUser = this.userCommandRepository.findByEmail(userRegisterDTO.getEmail());
        UserEvent userEvent = new UserEvent("userRegisterEvent", savedUser);
        kafkaTemplate
                .send("userManagementTopic", userEvent);
    }

    private void mapUser(UserCommandEntity user, UserRegisterDTO userRegisterDTO) {
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(hashPassword(userRegisterDTO.getPassword()));
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        Authority authority = new Authority();
        authority.setAuthority(userRegisterDTO.getRole());
        this.authorityCommandRepository.save(authority);
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
            }
        }
    }

    public String findUserByEmail(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        UserCommandEntity user = userCommandRepository.findByEmail(value);
        if (user == null) {
            return null;
        } else {
            return user.getEmail();
        }
    }
}
