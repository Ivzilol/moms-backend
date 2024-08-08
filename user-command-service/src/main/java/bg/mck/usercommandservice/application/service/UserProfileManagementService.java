package bg.mck.usercommandservice.application.service;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.dto.UserCredentialsDTO;
import bg.mck.usercommandservice.application.dto.UserDetailsDTO;
import bg.mck.usercommandservice.application.dto.UserStatusDTO;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.ProfileStatusUpdatedEvent;
import bg.mck.usercommandservice.application.events.ProfileUpdatedEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.exceptions.InvalidPasswordException;
import bg.mck.usercommandservice.application.exceptions.UserNotFoundException;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;


import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserProfileManagementService {

    private final UserRepository userRepository;
    private final UserQueryServiceClient userQueryClient;
    private final ObjectMapper objectMapper;
    private final AuthorityRepository authorityRepository;

    public UserProfileManagementService(UserRepository userRepository, UserQueryServiceClient userQueryClient, ObjectMapper objectMapper, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userQueryClient = userQueryClient;
        this.objectMapper = objectMapper;
        this.authorityRepository = authorityRepository;
    }

    public void updateUserStatus(Long id, UserStatusDTO dto) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        userEntity.setActive(dto.isActive());
        userRepository.save(userEntity);

        ProfileStatusUpdatedEvent event = new ProfileStatusUpdatedEvent(
              EventType.UserStatusUpdated,
                id,
                dto.isActive()
        );

        UserEvent<ProfileStatusUpdatedEvent> userEvent = EventCreationHelper.toUserEvent(event);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), event.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserProfile(Long id, UserDetailsDTO dto) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        Set<Authority> roles = new HashSet<>();

        if (dto.getRole() != null && dto.getRole().equals(AuthorityEnum.USER)) {
            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER));
        } else if (dto.getRole() != null && dto.getRole().equals(AuthorityEnum.ADMIN)) {

            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER));
            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.ADMIN));
        } else if (dto.getRole() != null && dto.getRole().equals(AuthorityEnum.SUPERADMIN)){

            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.USER));
            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.ADMIN));
            roles.add(authorityRepository.getAuthorityByAuthority(AuthorityEnum.SUPERADMIN));
        }

        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setEmail(dto.getEmail());
        userEntity.setAuthorities(roles);
        userRepository.save(userEntity);

        ProfileUpdatedEvent event = new ProfileUpdatedEvent(
                EventType.UserProfileUpdated,
                id,
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber(),
                roles.stream().map(r -> r.getAuthority().name()).collect(Collectors.toSet())
        );

        UserEvent<ProfileUpdatedEvent> userEvent = EventCreationHelper.toUserEvent(event);
        try {
            userQueryClient.sendEvent(objectMapper.writeValueAsString(userEvent), event.getEventType().name());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
