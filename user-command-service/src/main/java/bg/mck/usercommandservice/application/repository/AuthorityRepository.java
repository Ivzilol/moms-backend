package bg.mck.usercommandservice.application.repository;

import bg.mck.usercommandservice.application.entity.Authority;
import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getAuthorityByAuthority(AuthorityEnum authorityEnum);
}
