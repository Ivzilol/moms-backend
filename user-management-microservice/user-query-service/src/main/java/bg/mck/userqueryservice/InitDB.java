package bg.mck.userqueryservice;

import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitDB implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public InitDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            UserEntity user = new UserEntity(
                    "boroto_vr@abv.bg",
                    BCrypt.hashpw("borovaneca", BCrypt.gensalt()),
                    "Petyo",
                    "Veselinov",
                    "08933523",
                    Set.of("user", "admin")
            );

            user.setId("1");
            userRepository.save(user);
        }
    }
}
