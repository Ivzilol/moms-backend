package bg.mck.usercommandservice.application.repository;

import bg.mck.usercommandservice.application.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    ForgotPassword findByUuid(String uuid);

    ForgotPassword findByUserEmail(String email);
}
