package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.events.RegisteredUserEvent;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserRegistrationService {

    private final UserRepository userRepository;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processUserRegister(RegisteredUserEvent userEvent) {
            UserEntity user = new UserEntity();
        EventService.registerUserEvent(user, userEvent);
        this.userRepository.save(user);
    }
}
