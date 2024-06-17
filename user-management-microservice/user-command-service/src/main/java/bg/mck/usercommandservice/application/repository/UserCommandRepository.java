package bg.mck.usercommandservice.application.repository;

import bg.mck.usercommandservice.application.entity.UserCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommandRepository extends JpaRepository<UserCommandEntity, Long> {
    UserCommandEntity findByEmail(String value);
}
