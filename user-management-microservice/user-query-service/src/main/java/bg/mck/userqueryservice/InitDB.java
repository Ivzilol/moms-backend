package bg.mck.userqueryservice;


import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.apache.sshd.common.config.keys.loader.openssh.kdf.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitDB implements CommandLineRunner {

    private final UserRepository userRepository;


    public InitDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findAll().isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId("1");
            userEntity.setEmail("john.doe@example.com");
            userEntity.setPassword(BCrypt.hashpw("securePassword123", BCrypt.gensalt()));
            userEntity.setFirstName("John");
            userEntity.setLastName("Doe");
            userEntity.setPhoneNumber("+1234567890");
            userEntity.setActive(true);
            userEntity.setRoles(Set.of("ROLE_ADMIN"));

            userRepository.save(userEntity);
        }
    }
}

