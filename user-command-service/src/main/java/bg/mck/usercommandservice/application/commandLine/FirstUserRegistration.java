package bg.mck.usercommandservice.application.commandLine;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.enums.EventType;
import bg.mck.usercommandservice.application.events.RegisteredUserEvent;
import bg.mck.usercommandservice.application.events.UserEvent;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FirstUserRegistration implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserQueryServiceClient userQueryClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public FirstUserRegistration(AuthorityRepository authorityRepository, UserRepository userRepository, UserQueryServiceClient userQueryClient, ObjectMapper objectMapper) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.userQueryClient = userQueryClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Set<Authority> authorities = Arrays.stream(AuthorityEnum.values())
                    .map(authority -> {
                        Authority authorityEntity = new Authority();
                        authorityEntity.setAuthority(authority);
                        return authorityEntity;
                    }).collect(Collectors.toSet());


            authorityRepository.saveAll(authorities);

            UserEntity savedUser = new UserEntity()
                    .setActive(true)
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setEmail("admin@mck.bg")
                    .setPassword(BCrypt.hashpw("Admin", BCrypt.gensalt()))
                    .setPhoneNumber("0888888888")
                    .setAuthorities(authorities);

            userRepository.save(savedUser);

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
    }
}
