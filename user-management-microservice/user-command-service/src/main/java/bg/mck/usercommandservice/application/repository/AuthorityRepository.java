package bg.mck.usercommandservice.application.repository;

import bg.mck.usercommandservice.application.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
