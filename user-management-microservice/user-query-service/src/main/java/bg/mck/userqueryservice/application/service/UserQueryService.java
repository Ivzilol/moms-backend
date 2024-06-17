package bg.mck.userqueryservice.application.service;

import bg.mck.usercommandservice.application.dto.Authority;
import bg.mck.usercommandservice.application.dto.UserEvent;
import bg.mck.userqueryservice.application.entity.UserQueryEntity;
import bg.mck.userqueryservice.application.repository.UserQueryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;

    public UserQueryService(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @KafkaListener(topics = "userManagementTopic", groupId = "user-query-service")
    public void processUserRegister(UserEvent userEvent) {
        if (userEvent.getEventType().equals("userRegisterEvent")) {
            UserQueryEntity user = new UserQueryEntity();
            user.setId(userEvent.getUserCommandEntity().getId());
            user.setEmail(userEvent.getUserCommandEntity().getEmail());
            user.setPassword(userEvent.getUserCommandEntity().getPassword());
            user.setFirstName(userEvent.getUserCommandEntity().getFirstName());
            user.setLastName(userEvent.getUserCommandEntity().getLastName());
            user.setPhoneNumber(userEvent.getUserCommandEntity().getPhoneNumber());
            user.setActive(userEvent.getUserCommandEntity().isActive());
            Set<String> roles = userEvent.getUserCommandEntity().getAuthorities()
                    .stream()
                    .map(Authority::getAuthority)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
            this.userQueryRepository.save(user);
        }
    }
}
