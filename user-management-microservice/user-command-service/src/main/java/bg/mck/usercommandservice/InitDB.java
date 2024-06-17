package bg.mck.usercommandservice;


import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import org.apache.sshd.common.config.keys.loader.openssh.kdf.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitDB implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


    public InitDB(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findAll().isEmpty()) {
            Authority roleAdmin = new Authority();
            roleAdmin.setAuthority(AuthorityEnum.admin);

            authorityRepository.save(roleAdmin);

            Set<Authority> authorities = new HashSet<>();
            authorities.add(roleAdmin);


            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("john.doe@example.com");
            userEntity.setPassword(BCrypt.hashpw("securePassword123", BCrypt.gensalt()));
            userEntity.setFirstName("John");
            userEntity.setLastName("Doe");
            userEntity.setPhoneNumber("+1234567890");
            userEntity.setActive(true);
            userEntity.setAuthorities(authorities);

            userRepository.save(userEntity);
        }
    }
}

