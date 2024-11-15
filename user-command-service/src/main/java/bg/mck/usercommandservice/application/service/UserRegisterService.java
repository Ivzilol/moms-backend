package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.ErrorsRegistrationDTO;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.RegisteredUserEvent;
import bg.mck.usercommandservice.application.dto.UserRegisterDTO;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static bg.mck.usercommandservice.application.exceptions.ErrorUserRegistrationExceptions.*;
import static bg.mck.usercommandservice.application.utils.PasswordUtil.hashPassword;

@Service
public class UserRegisterService {


    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserQueryServiceClient userQueryClient;
    private final ObjectMapper objectMapper;


    public UserRegisterService(AuthorityRepository authorityRepository, UserRepository userRepository, UserQueryServiceClient queryServiceClient, ObjectMapper objectMapper) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.userQueryClient = queryServiceClient;
        this.objectMapper = objectMapper;
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

        UserEvent<RegisteredUserEvent> userEvent = EventCreationHelper.toUserEvent(event);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), event.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapUser(UserEntity user, UserRegisterDTO userRegisterDTO) {
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(hashPassword(userRegisterDTO.getPassword()));
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        user.setActive(true);
        Authority authority = new Authority(authorityRepository.getAuthorityByAuthority(AuthorityEnum.valueOf(userRegisterDTO.getRole())));
        if (user.getAuthorities() == null) {
            user.setAuthorities(new HashSet<>());
        }

        if (authority.getAuthority().equals(AuthorityEnum.SUPERADMIN)) {
            user.getAuthorities().addAll(authorityRepository.findAll());

        } else if (authority.getAuthority().equals(AuthorityEnum.ADMIN)) {
            user.getAuthorities().addAll(List.of(authorityRepository.getAuthorityByAuthority(AuthorityEnum.ADMIN),
                    authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER)));

        } else {
            user.getAuthorities().add(authority);
        }
    }


    public void setErrors(List<String> errors, ErrorsRegistrationDTO errorsRegistrationDTO) {
        for (String error : errors) {
            switch (error) {
                case EMPTY_EMAIL:
                    errorsRegistrationDTO.setEmailError(EMPTY_EMAIL);
                case EMPTY_PASSWORD:
                    errorsRegistrationDTO.setPasswordError(EMPTY_PASSWORD);
                case EMPTY_CONFIRM_PASSWORD:
                    errorsRegistrationDTO.setConfirmPasswordError(EMPTY_CONFIRM_PASSWORD);
                case EMPTY_FIRST_NAME:
                    errorsRegistrationDTO.setFirstNameError(EMPTY_FIRST_NAME);
                case EMPTY_LAST_NAME:
                    errorsRegistrationDTO.setLastNameError(EMPTY_LAST_NAME);
                case EMPTY_PHONE_NUMBER:
                    errorsRegistrationDTO.setPhoneNumberError(EMPTY_PHONE_NUMBER);
                case INVALID_EMAIL:
                    errorsRegistrationDTO.setEmailError(INVALID_EMAIL);
                case EXITING_EMAIL:
                    errorsRegistrationDTO.setEmailError(EXITING_EMAIL);
                case SHORT_PASSWORD:
                    errorsRegistrationDTO.setPasswordError(SHORT_PASSWORD);
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
