package bg.mck.usercommandservice.application.init;

import bg.mck.usercommandservice.application.client.UserQueryServiceClient;
import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.entity.UserEntity;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import bg.mck.usercommandservice.application.repository.AuthorityRepository;
import bg.mck.usercommandservice.application.repository.UserRepository;
import bg.mck.usercommandservice.application.service.UserRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static bg.mck.usercommandservice.application.utils.PasswordUtil.hashPassword;

@Component
public class InitSuperAdmin implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final ObjectMapper objectMapper;

    private final UserQueryServiceClient userQueryClient;

    @Value("${SUPERADMIN_PASSWORD}")
    private String passwordSuperAdmin;

    public InitSuperAdmin(UserRepository userRepository, AuthorityRepository authorityRepository, ObjectMapper objectMapper, UserQueryServiceClient userQueryClient) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.objectMapper = objectMapper;
        this.userQueryClient = userQueryClient;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setEmail("super-admin@email.bg");
            user.setPassword(hashPassword(passwordSuperAdmin));
            user.setActive(true);
            user.setFirstName("Admin");
            user.setLastName("Adminov");
            user.setPhoneNumber("0888776655");
            if (user.getAuthorities() == null) {
                user.setAuthorities(new HashSet<>());
            }
            Authority authority = new Authority();
            authority.setAuthority(AuthorityEnum.SUPERADMIN);
            this.authorityRepository.save(authority);
            user.getAuthorities().add(authority);
            Authority authorityAdmin = new Authority();
            authorityAdmin.setAuthority(AuthorityEnum.ADMIN);
            this.authorityRepository.save(authorityAdmin);
            user.getAuthorities().add(authorityAdmin);
            Authority authorityUser = new Authority();
            authorityUser.setAuthority(AuthorityEnum.USER);
            this.authorityRepository.save(authorityUser);
            user.getAuthorities().add(authorityUser);
            this.userRepository.save(user);

            UserEntity savedUser = this.userRepository.findByEmail("super-admin@email.bg");

            UserRegisterService.registerUser(savedUser, userQueryClient, objectMapper);
        }
    }
}
